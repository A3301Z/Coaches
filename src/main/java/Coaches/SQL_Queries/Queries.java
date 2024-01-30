package Coaches.SQL_Queries;

public enum Queries {
    SELECT_COACH_FROM_DB("SELECT * FROM coach"),
    UPDATE_ARCHIVED_STATUS_SQL("UPDATE coach SET archived = ? WHERE id = ?"),

    ADD_NEW_COACH_SQL("""
                              INSERT INTO coach (id, firstname, secondname, age, birthday, phonenumber, email, archived)
                              VALUES (?, ?, ?, ?, ?, ?, ?, ?);"""),

    UPDATE_COACH_FIELD("""
                               UPDATE coach SET
                               firstname = ?,
                               secondname = ?,
                               age = ?,
                               birthday = ?,
                               phonenumber = ?,
                               email = ?,
                               archived = ?
                               WHERE id = ?"""),

    DELETED_COACH("DELETE FROM coach WHERE id = ?"),

    ADD_PHOTO("""
                      INSERT INTO coach_image (id, coach_id, file_name, content, is_main)
                      VALUES (?,?,?,?,?);"""),

    ADD_MAIN_PHOTO("""
                           INSERT INTO coach_image (id, coach_id, file_name, content, is_main)
                           VALUES (?,?,?,?,?);"""),

    GET_MAIN_PHOTO("SELECT content FROM coach_image WHERE coach_id = ? AND is_main = true;"),

    DO_ALL_NOT_MAIN("UPDATE coach_image SET is_main = false WHERE coach_id = ? AND is_main = true;"),

    PHOTO_IS_EXISTS("SELECT * FROM coach_image WHERE coach_id = ? AND file_name = ?");

    private final String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
