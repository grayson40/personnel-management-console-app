public class Lecture {
    String crn;
    String prefix;
    String title;
    String level;
    String modality;
    String room;
    boolean hasLab;

    public Lecture(String crn, String prefix, String title, String level, String modality, String room, boolean hasLab) {
        this.crn = crn;
        this.prefix = prefix;
        this.title = title;
        this.level = level;
        this.modality = modality;
        this.room = room;
        this.hasLab = hasLab;
    }

    // public Lecture(String crn, String prefix, String title, String level, String modality) {
    //     this.crn = crn;
    //     this.prefix = prefix;
    //     this.title = title;
    //     this.level = level;
    //     this.modality = modality;
    // }

    @Override
    public String toString() {
        return "Lecture [crn=" + crn + ", prefix=" + prefix + ", title=" + title + ", level=" + level + ", modality="
                + modality + ", room=" + room + ", hasLab=" + hasLab + "]";
    }
}
