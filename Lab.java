public class Lab {
    String crn;
    String room;
    String lectureCrn;

    public Lab(String lectureCrn, String crn, String room) {
        this.lectureCrn = lectureCrn;
        this.crn = crn;
        this.room = room;
    }

    @Override
    public String toString() {
        return crn + "," + room;
    }
}
