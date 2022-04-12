Opperation of application
After visiting this GitHub repository, the user should have a version of android studio installed on there computer or a similar IDE. 

•	Once downloaded allow the Gradle files to build, depending on the computer speed this may take a minute for the first time.

•	Then you have the option if your using Android studio if you wish to use the emulator to run this project or use your physical device. To run it on a physical device it advised to connect your pc to the device using a USB cable. From here you go to settings on your device, select developer options.

o	 To do this go to settings and then select “About device” or “About phone”, next scroll down to build number and tap it seven times. You may need to enter your password now and then its enabled 

![image](https://user-images.githubusercontent.com/49272530/163059988-bba9a331-408e-4c09-a677-cb41b882247a.png)

•	Once developer options are on, then enable USB debugging (if applicable). Select device manager if running on Android studio and select physical.

- Emulator

•	To run the application on the emulator in android studio go to device manager again on create virtual device. From here select and android device (preferably with play store) 

![image](https://user-images.githubusercontent.com/49272530/163060186-af8c836f-2da6-4ac0-bc07-18fdb6257a80.png)

•	Next is to choose a system image, we went with Oreo ourselves for our device.

![image](https://user-images.githubusercontent.com/49272530/163060207-443c30c9-d664-49cd-8805-cfd387a079f6.png)

Now give the device a name and you are up and running.

There is on more option to run the front end and that’s to go int6o android studio to Build, then dropping down Build Bundle APK’s and selecting build APK. If you send this file to a mobile device and download it, you will be able to run the application on that device without requiring the USB connection.

You are now able to run the application, the backend is hosted on the cloud so there is no need to run locally but if you would like to you can run the backend on docker.

•	To run it locally you must have docker desktop installed, then you must run docker-compose up in the main file and this will launch all the services on docker. It may take a minute or two all instances to be running.

![image](https://user-images.githubusercontent.com/49272530/163060435-9ad9a0cf-d185-47b2-8fe1-2cf844e4cee2.png)


# GET
	http://localhost:8080/api/test/user
	http://localhost:8080/api/test/mod
	http://localhost:8080/api/test/all

# POST
	http://localhost:8080/api/auth/signup
	http://localhost:8080/api/auth/signin

# PUT
	

# DELETE
	

# Requests



