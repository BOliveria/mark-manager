/**
 * All of the popup classes serve the same function which is to let the user know whether certain 
 * operations were done successfully or if they failed and why. They all have the same variables 
 * with the same fuctions. The only difference with each popup is the message they output to the user
 * 
 * Below is a list of the popups and when each one is used.
 * 
 * ConfirmPopup - Let's the user know when a course or mark was added successfully
 * DupePopup - Let's the user know when the record they are trying to add already exists in the database
 * ErrorPopup - Let's the user know something has gone wrong. If this appears the developer should be contacted.
 * InvalidPopup - Let's the user know when they are missing information to input when adding new records
 * NullPopup - Let's the user know when the record they are looking for does not exist in the database (deleting marks/courses)
 * RemoveConfirmPopup - A distince confirm message for the user when removing records from the database
 * SwitchPopup - Let's the user know when they have successfully switched to a different course
 */

abstract class Popup {

    abstract void display();

}
