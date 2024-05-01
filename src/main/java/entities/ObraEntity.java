package entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "obra", schema = "constructoraH", catalog = "")
public class ObraEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;
    @Basic
    @Column(name = "entrega", nullable = true)
    private Date entrega;
    @OneToMany(mappedBy = "obraByIdObra")
    private Collection<EmpleadoEntity> empleadosById;
    @OneToMany(mappedBy = "obraByIdObra")
    private Collection<MaquinariaEntity> maquinariasById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getEntrega() {
        return entrega;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObraEntity that = (ObraEntity) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(direccion, that.direccion) && Objects.equals(entrega, that.entrega);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, direccion, entrega);
    }

    public Collection<EmpleadoEntity> getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(Collection<EmpleadoEntity> empleadosById) {
        this.empleadosById = empleadosById;
    }

    public Collection<MaquinariaEntity> getMaquinariasById() {
        return maquinariasById;
    }

    public void setMaquinariasById(Collection<MaquinariaEntity> maquinariasById) {
        this.maquinariasById = maquinariasById;
    }
}
