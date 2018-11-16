package lv.helloit.trello.dto.task;

public enum TaskStatus {

    TODO,
    DOING,
    DONE;

    public static boolean contains(String str) {
        for (TaskStatus ts : TaskStatus.values()) {
            if (ts.name().equals(str.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

}
