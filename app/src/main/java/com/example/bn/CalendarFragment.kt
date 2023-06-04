package com.example.bn

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bn.api.SessionManager
import com.example.bn.dto.SlotRequestDto
import com.example.bn.dto.SlotsMapDto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : Fragment() {
    private lateinit var recyclerViewSlot: RecyclerView
    private lateinit var slotMasterAdapter: SlotMasterPersonalAdapter
    private lateinit var slotClientAdapter: SlotClientAdapter

    private val sessionManager = SessionManager.getInstance()
    private var slotClient: SlotsMapDto = SlotsMapDto()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
        val addSlotButton  = root.findViewById<FloatingActionButton>(R.id.addSlot)

        if (sessionManager.getUser().role== "MASTER") {
                addSlotButton.visibility = View.VISIBLE
            } else {
                addSlotButton.visibility = View.GONE
            }
        var fromDateTime: ZonedDateTime? = null
        var toDateTime: ZonedDateTime? = null

        var selectedDate: LocalDate? = null
        var selectedTime: LocalTime? = null
        addSlotButton.setOnClickListener{
            sessionManager.getCoroutineScope().launch {
                suspendCoroutine<Unit> { continuation ->
                    val datePicker = DatePickerDialog(requireContext())
                    datePicker.setOnDateSetListener { _, year, month, dayOfMonth ->
                        selectedDate = LocalDate.of(year, month + 1, dayOfMonth)

                        val timePicker = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
                            selectedTime = LocalTime.of(hourOfDay, minute)

                            fromDateTime = ZonedDateTime.of(selectedDate, selectedTime, ZoneId.systemDefault())

                            val toastText = "Slot time start chosen: $fromDateTime"
                            Toast.makeText(requireContext(), toastText, Toast.LENGTH_SHORT).show()

                            continuation.resume(Unit) // Возобновить выполнение после выбора времени начала
                        }, 0, 0, false)

                        timePicker.show()
                    }
                    datePicker.show()
                }

                suspendCoroutine<Unit> { continuation ->
                    val datePickerTwo = DatePickerDialog(requireContext())
                    datePickerTwo.setOnDateSetListener { _, year, month, dayOfMonth ->
                        selectedDate = LocalDate.of(year, month + 1, dayOfMonth)

                        val timePickerTwo = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
                            selectedTime = LocalTime.of(hourOfDay, minute)


                            toDateTime = ZonedDateTime.of(selectedDate, selectedTime, ZoneId.systemDefault())
                            val toastText = "Slot time end chosen: $toDateTime"
                            Toast.makeText(requireContext(), toastText, Toast.LENGTH_SHORT).show()

                            continuation.resume(Unit) // Возобновить выполнение после выбора времени окончания
                        }, 0, 0, false)

                        timePickerTwo.show()
                    }
                    datePickerTwo.show()
                }

                // Код здесь будет выполняться только после выбора обоих дат и времени
                addNewSlot(fromDateTime!!, toDateTime!!)
            }

        }



        initSlotPreview(root)
        return root

    }

    private fun initSlotPreview(root: View) {
        recyclerViewSlot = root.findViewById(R.id.recyclerView)
        recyclerViewSlot.setHasFixedSize(true)
        recyclerViewSlot.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        sessionManager.getCoroutineScope().launch {
            performGetMySlots()

            if (sessionManager.getUser().role == "CLIENT") {
                slotClientAdapter = SlotClientAdapter(slotClient)
                recyclerViewSlot.adapter = slotClientAdapter
            } else {
                slotMasterAdapter = SlotMasterPersonalAdapter(slotClient)
                recyclerViewSlot.adapter = slotMasterAdapter
            }
        }

    }

    private suspend fun performGetMySlots() {
        try {

            val response = sessionManager.getUserApi().getAllMySlots(sessionManager.getToken())
            if (response.isSuccessful) {
                slotClient = response.body() as SlotsMapDto
                Toast.makeText(requireContext(), "GotMasterService", Toast.LENGTH_SHORT).show()
            } else {
                val errorBody = response.errorBody()?.string()
                println("Error: $errorBody")
                Toast.makeText(requireContext(), "Error: $errorBody", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    private suspend fun addNewSlot(from:ZonedDateTime, to:ZonedDateTime) {
        try {
            val response = sessionManager.getUserApi().addNewSlot(sessionManager.getToken(),SlotRequestDto(from,to))
            if (response.isSuccessful) {
                Toast.makeText(requireContext(), "Added new Slot", Toast.LENGTH_SHORT).show()
            } else {
                val errorBody = response.errorBody()?.string()
                println("Error: $errorBody")
                Toast.makeText(requireContext(), "Error: $errorBody", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


}