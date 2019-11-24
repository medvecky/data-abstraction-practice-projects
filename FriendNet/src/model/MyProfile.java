package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyProfile {

    private String name;
    private int age;
    private String currentLocation;
    private String workPlace;
    private List<MyProfile> friendsList;
    private List<Event> upcomingEvents;

    public MyProfile(String nm, int age, String locn, String work) {
        this.name = nm;
        this.age = age;
        this.currentLocation = locn;
        this.workPlace = work;
        friendsList = new ArrayList<>();
        upcomingEvents = new ArrayList<>();
    }

    // getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public int upcomingEventNum() {
        return upcomingEvents.size();
    }

    public List<MyProfile> getFriendsList() {
        return friendsList;
    }

    public List<Event> getEventList() {
        return upcomingEvents;
    }

    // REQUIRES: f is not already in friendsList
    // MODIFIES: this
    // EFFECTS: consumes a MyProfile object, a friend f, and adds it to the friendsList
    public void addFriend(MyProfile f) {
        friendsList.add(f);
    }

    // MODIFIES: this
    // EFFECTS: removes a friend with the given name from this. If removal is successful, return true, else
    //          return false
    public boolean unFriend(String nm) {
        if (friendsList.size() > 0) {
            Optional<MyProfile> foundFriend = friendsList.stream()
                    .filter(myProfile -> myProfile.getName().equals(nm))
                    .findFirst();
            if (foundFriend.isPresent()) {
                friendsList.remove(foundFriend.get());
                return true;
            }
        }
        return false;
    }

    // REQUIRES: ev is not in upcomingEvents
    // MODIFIES: this
    // EFFECTS: adds the given event to the list of upcoming events
    public void addEvent(Event ev) {
        upcomingEvents.add(ev);
    }

    // MODIFIES: this
    // EFFECTS: removes an event with the given name. If removal is successful, return true, else return false
    public boolean removeEvent(String nm) {
        if (upcomingEvents.size() > 0) {
            Optional<Event> foundEvent = upcomingEvents.stream()
                    .filter(event -> event.getName().equals(nm))
                    .findFirst();
            if (foundEvent.isPresent()) {
                upcomingEvents.remove(foundEvent.get());
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the number of events that are at the current location of this person
    public int eventNumNearMe() {
        return (int) upcomingEvents.stream()
                .filter(event -> event.getLocation().equals(currentLocation)).count();
    }

    // EFFECTS: returns the number of events of the given type et
    public int specificEventType(EventType et) {
        return (int) upcomingEvents.stream()
                .filter(event -> event.getEventType().equals(et)).count();
    }

    // EFFECTS: prints events of  type et to the console
    //          Hint: is there a method you have already written that you can use?
    public void printEventSchedule(EventType et) {
        upcomingEvents.stream()
                .filter(event -> event.getEventType().equals(et))
                .forEach(event -> System.out.println(event.getName()));
    }

    // EFFECTS: prints out the list of friends that this MyProfile has
    public void printFriendNames() {
       friendsList.forEach(friend -> System.out.println(friend.getName()));
    }

    // EFFECTS: prints out the names of friends who live at the same location associated with this profile
    public void printFriendsNearMe() {
        friendsList.stream()
                .filter(friend -> friend.getCurrentLocation().equals(currentLocation))
                .forEach(friend -> System.out.println(friend.getName()));
    }

    // EFFECTS: produces true if this profile has a friend with the given name,
    //          OR if any of this profile's friends has a friend with the given name
    //          Hint: use recursion!
    public boolean canFindPerson(String name) {
        if (this.friendsList.isEmpty()) return false;

        for (MyProfile p : friendsList) {
            if (p.getName().equals(name)) {
                return true;
            }
        }

        for (MyProfile p : friendsList) {
            if (p.canFindPerson(name)) {
                return true;
            }
        }
        return false;
    }


}
