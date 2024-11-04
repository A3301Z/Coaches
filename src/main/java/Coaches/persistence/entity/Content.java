package Coaches.persistence.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@Table("content")
public class Content {

    @Id
    private UUID id;

    @Column("coach_id")
    private UUID coachId;

    @Column("file_name")
    private String fileName;

    @Column("photo")
    private byte[] photo;

    @Column("is_main")
    private boolean isMain;
}
