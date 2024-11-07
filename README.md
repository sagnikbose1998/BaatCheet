
# BaatCheet

Baatcheet is a smart contact management application designed to streamline contact handling with an intuitive interface and comprehensive features. It allows users to manage contacts efficiently, send emails directly from the app, store images in the cloud, and toggle between themes.



## Features

- User Signup with Email and Password - Users can create an account using their email and password.
- Dark/Light Theme - Switch between dark and light modes for a personalized experience
- Email Verification - Email verification link is sent to activate the account.
- Social Login - Supports sign-up through Google and GitHub.
- Add Contact with Picture - Users can add contacts with profile pictures.
- Cloud Storage for Contact Pictures - Stores profile pictures securely in AWS or Cloudinary.
- View All Contacts - Users can view a list of all their contacts.
- Update and Delete Contacts - Modify or remove contact information as needed.
- Search Contacts - Quickly search for contacts by name or other fields.
- Pagination - Efficiently navigate through large lists of contacts.
- Export Contact Data - Export contact data in various formats (e.g., CSV, PDF).


## Usage/Examples

- Sign Up - Register with an email and verify your account.
- Manage Contacts - Add, update, delete, and search contacts.
- Send Emails - Compose and send emails with attachments.
- Switch Themes - Toggle between dark and light themes.


## Installation

### Prerequisites

Before you begin, ensure you have the following installed on your local machine:

- **Java 8+** - Make sure Java is installed on your system.
- **Maven** - Required for dependency management and building the project.
- **MySQL** - Database server for storing user and contact data.

### Steps

1. **Clone the Repository**

   Open a terminal and run:

   git clone https://github.com/your-username/baatcheet.git
   cd baatcheet
   
3. **Set Up database**

   
   Create a MySQL database named baatcheet (or a different name of your choice).
   Update the application.properties file with your MySQL configuration:

   spring.datasource.url=jdbc:mysql://localhost:3306/baatcheet
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD

3. **Configure Cloud Storage for Contact Photos**

   AWS configuration
   cloud.aws.access-key=YOUR_AWS_ACCESS_KEY
   cloud.aws.secret-key=YOUR_AWS_SECRET_KEY

   OR Cloudinary configuration
   cloudinary.cloud-name=YOUR_CLOUD_NAME
   cloudinary.api-key=YOUR_API_KEY
   cloudinary.api-secret=YOUR_API_SECRET

   
5. **Build and Run the Application**

   Make sure Maven is installed and run the following commands:
   mvn clean install
   mvn spring-boot:run
   
7. **Access Baatcheet**
   
   Once application is running, access the application
   http://localhost:8080



