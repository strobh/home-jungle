package se.bth.homejungle.storage.entity.view;

/**
 * CalendarEventType represents the type of event of an {@link CalendarEvent}.
 *
 * The type can either be to water a plant or to plant a new plant.
 */
public enum CalendarEventType {
    WATER("water"), PLANT("plant");

    /**
     * The string representation of the event type.
     */
    private final String type;

    /**
     * Creates a new CalendarEventType with a given string representation.
     *
     * @param type String representation of the event type.
     */
    CalendarEventType(String type) {
        this.type = type;
    }

    /**
     * Returns the string representation of the event type.
     *
     * @return String representation of the event type.
     */
    public String getType() {
        return type;
    }
}
