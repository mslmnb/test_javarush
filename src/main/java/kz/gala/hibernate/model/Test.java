package kz.gala.hibernate.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by loiskrg on 25.07.2016.
 */
@Entity
public class Test {
    private int id;
    private String name;
    private Boolean isready;
    private Date createddate;
    private Date readydate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "isready")
    public Boolean getIsready() {
        return isready;
    }

    public void setIsready(Boolean isready) {
        this.isready = isready;
    }

    @Basic
    @Column(name = "createddate")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    @Basic
    @Column(name = "readydate")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date getReadydate() {
        return readydate;
    }

    public void setReadydate(Date readydate) {
        this.readydate = readydate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (id != test.id) return false;
        if (name != null ? !name.equals(test.name) : test.name != null) return false;
        if (isready != null ? !isready.equals(test.isready) : test.isready != null) return false;
        if (createddate != null ? !createddate.equals(test.createddate) : test.createddate != null) return false;
        if (readydate != null ? !readydate.equals(test.readydate) : test.readydate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isready != null ? isready.hashCode() : 0);
        result = 31 * result + (createddate != null ? createddate.hashCode() : 0);
        result = 31 * result + (readydate != null ? readydate.hashCode() : 0);
        return result;
    }
}
