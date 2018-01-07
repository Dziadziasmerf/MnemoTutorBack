package pl.corp.dziadzia.MnemoTutorBack.model;

public enum Discipline {
    SPEED_CARDS("Speed cards"),
    FIVE_MINUTES_NUMBERS("5 minutes numbers");

    private String name;

    Discipline(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Discipline getDisciplineByName(String name) {
        for(Discipline discipline: Discipline.values()) {
            if(discipline.getName().equalsIgnoreCase(name))
                return discipline;
        }
        throw new IllegalArgumentException();
    }
}
