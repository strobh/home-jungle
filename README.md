# HomeJungle

HomeJungle helps newbies or people with close to zero experience to get started with indoor plants. 

The user can search for seeds and seedlings from other users or a local community nearby. Additionally, he can plan which plants he wants to grow and the app reminds him when to start planting the seeds.

The app will provide the possibility to search for different plants and get information about them like how much water or sun the plant needs. The user can add the plants he/she owns in a personal area and get notified when he/she is supposed to water them.


## Mobile Functionalities Requirements
- Multiple activities or fragments
	- We have more than 15 fragments, e.g., plants, future plants, calender, database (categories, species and information), marketplace, give-aways, etc.
- Utilization of sensors
	- We use the GPS location in the marketplace to only display give-aways in the neighbourhood of the user
	- We use the GPS location when a give-away is created by the user to only display it to nearby users (see point above)
	- We use the camera to take photos of give-aways when a give-away is created
- Use of notifications
	- The user receives a daily reminder when he needs to water a plant or plant a future plant
- Utilization of touch (not just click). Swipe
	- In the home/plant fragment, the user can swipe between two pages (plants and future plants)
	- In the plant species information fragment, the user can swipe between two pages (general information and information on how to plant)
	- Items in lists can be deleted by swiping them (e.g., in the home/plant fragment, in the give-aways, etc.)
- Networking (e.g. http)
	- Firebase database to store and retrieve give-aways on a server
	- Google Map to display the location of a give-away on a map
- Using a variety of interface elements (e.g. buttons, progress bars, etc)
	- We used a map, buttons, progress bars, alert dialogs, toasts, checkboxes, list views, images, etc.
