# Beauty Note Application

### What is that?

Beauty Note is a fantastic application designed to make life easier for beauty masters and their clients. This app serves as a convenient platform where beauty masters can offer their services in a comfortable way. It streamlines the entire process by allowing masters to configure their available time slots, showcase their services, and enable clients to choose the desired slot and service with ease.

With Beauty Note, masters no longer have to manually manage bookings. They can simply handle booking requests by pressing a few buttons within the app. Clients also benefit greatly as they can make advance payments or pay for the entire service online, eliminating the hassle of carrying cash.

After a client visits a master, they can leave a review on the app, sharing their feedback on the specific service they received. This feedback is visible to everyone, helping other clients make informed decisions when choosing a master or service.

One of the most convenient features of Beauty Note is that clients can communicate directly with masters through the app. There's no need to make phone calls or search for contact information. Everything can be done seamlessly within the application.

Looking ahead, Beauty Note has plans to introduce a marketplace where masters can showcase and sell their own products. This means that clients will have the opportunity to purchase their favorite master's products online, all within the Beauty Note environment.

In summary, Beauty Note is a user-friendly app that simplifies interactions between beauty masters and their clients. It provides a unified platform for booking, payment, reviews, communication, and even shopping, all tailored to enhance the beauty experience for everyone involved.

### Technology stack

Android application:
- Kotlin
- Retrofit
- Facebook SDK

[Used REST web service (self-written)](https://github.com/GrEFeRFeeD/beauty-note-api):
- Java
- Spring
  - Boot
  - Data
    - Hibernate
    - PostgreSQL
  - Security
    - OAuth2
  - Web
- Facebook API

## Showcase 

Here you can find the demo showcases of the beta version of the project.

### Client Showcase

https://github.com/mariiasirenko2/BN/assets/66921192/5ae7eb71-10c9-4ce0-be68-d02166794f89

### Master Showcase

https://github.com/mariiasirenko2/BN/assets/66921192/f7e3286f-764d-49d1-999b-3583cc3495fa

## Available features

At this point you can easily:
- register via Facebook account
- be master:
  - create your own service propositions
  - choose services of which beauty groups you want to offer
  - define costs, average time, description of each service
  - create time slots when you want clients to visit you
  - apply clients booking requests
  - enable push notifications, so you will be informed when new client appears
- be client:
  - search for type of service you want
  - search for type of masters you want
  - search for certain service of certain masters you prefer
  - choose best time to visit a master
  - enable push notifications, so you will be informed when master approved the booking

In the nearest future you will be able to:
- pay for the services online as client
- leave a feedback for attended services as client
- set up autoconfiguration of slots creation process as master
- message with masters or clients
