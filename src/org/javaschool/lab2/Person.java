package org.javaschool.lab2;

public class Person {
    private final boolean man;
    private final String name;
    private Person spouse = null;

    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife. Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     *
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     */
    public boolean marry(Person person) {
        if (person != null && this.man ^ person.man && this.spouse != person) {
            this.divorce();
            person.divorce();
            this.spouse = person;
            person.spouse = this;

            System.out.println("Just married: " + this.name + "&" + person.name);
            return true;
        } else {
            System.out.println("Denied: " + this.name + "&" +(person == null ? "NULL" : person.name));
            return false;
        }
    }

    /**
     * Sets spouse = null if spouse is not null
     *
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        if (this.spouse != null) {
            System.out.println("divorce: " + this.name + "&" + this.spouse.name);
            this.spouse.spouse = null;
            this.spouse = null;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Person ivan = new Person(true, "Ivan");
        Person petr = new Person(true, "Petr");
        Person masha = new Person(false, "Masha");

        ivan.marry(petr);
        ivan.marry(masha);
        ivan.marry(masha);
        petr.marry(masha);
    }
}
