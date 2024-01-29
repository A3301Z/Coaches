package Coaches.SQL_Queries;

public enum Queries {
    SELECT_COACH_FROM_DB("SELECT * FROM coach"),
    UPDATE_ARCHIVED_STATUS_SQL("UPDATE coach SET archived = ? WHERE id = ?"),
    ADD_NEW_COACH_SQL(
            """
                    INSERT INTO coach (id, firstname, secondname, age, birthday, phonenumber, email, archived)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?);"""),

    UPDATE_COACH_FIELD(
                """
                UPDATE coach SET
                firstname = ?,
                secondname = ?,
                age = ?,
                birthday = ?,
                phonenumber = ?,
                email = ?,
                archived = ?
                WHERE id = ?"""),
    DELETED_COACH("DELETE FROM coach WHERE id = ?");

    private final String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
