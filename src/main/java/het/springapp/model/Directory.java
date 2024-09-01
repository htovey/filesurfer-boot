package het.springapp.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DIRECTORY")

@NamedQueries({
        @NamedQuery(name="Directory.getDirectories", query="SELECT d FROM Directory d")
})

public class Directory implements Serializable {

    private static final long serialVersionUID = 7746627867118059647L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "label")
    private String label;
    @Column(name = "value")
    private String value;

    public Directory() {}

    public Directory(String[] values) {
       setLabel(values[1]);
        setValue(values[2]);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
