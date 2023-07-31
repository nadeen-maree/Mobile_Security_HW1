# Android Login Application

Welcome to the Android Login Application! This is a simple Android app that allows users to log in by meeting certain device condition requirements. The app checks five conditions related to the Android device, and users must fulfill all the conditions to gain access.

## Features

- Simple and intuitive login page with a password field and login button.
- Validates five conditions for device approval.
- Customizable passwords for each condition.
- Provides feedback on condition status and login success/failure.

## How to Use

Follow these steps to use the Android Login Application:

1. Clone the repository to your local machine:
   `git clone https://github.com/nadeen-maree/Mobile_Security_HW1.git`

2. Open the project in Android Studio.

3. Build and run the application on your Android device or emulator.

4. On the login page, you will see a text field to enter the password and a login button.

5. To log in successfully, you must meet all the following conditions:

    a. **Full Battery**: Ensure your device has a full battery charge.

    b. **Screen Size 1000x1000**: Confirm that your device's screen size is exactly 1000x1000 pixels.

    c. **Network Connection**: Make sure your device is connected to a network (cellular data or Wi-Fi).

    d. **Wi-Fi On**: Ensure that the Wi-Fi on your device is turned on.

    e. **Bluetooth On**: Verify that the Bluetooth on your device is turned on.

6. Enter the correct passwords for each condition to proceed with the login.

## Configuration

You can customize the passwords for each condition in the `strings.xml` file located in the `app/src/main/res/values/strings.xml` directory. Modify the following variables to set the passwords:

```xml
<resources>

    <string name="battery_password">battery</string>
    <string name="wifi_password">wifi</string>
    <string name="network_password">network</string>
    <string name="bluetooth_password">bluetooth</string>
    <string name="screen_size_password">screen</string>
    <string name="all_conditions_password">all</string>

</resources>
```

## Dependencies

This Android application uses the following dependencies:

- `androidx.appcompat:appcompat`
- `com.google.android.material:material`
- Other default dependencies provided by Android Studio.

## Contributions

Contributions to this project are welcome. If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

---

Have fun using the Android Login Application! If you have any questions or need further assistance, please don't hesitate to contact us.


