package entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "empleado", schema = "constructoraH", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "dni", nullable = false, length = 9)
    private String dni;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "sueldo", nullable = true, precision = 0)
    private Double sueldo;
    @Basic
    @Column(name = "idObra", nullable = true)
    private Integer idObra;
    @ManyToOne
    @JoinColumn(name = "idObra", referencedColumnName = "id")
    private ObraEntity obraByIdObra;
    @OneToMany(mappedBy = "empleadoByIdEmpleado")
    private Collection<MaquinariaEntity> maquinariasById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Integer getIdObra() {
        return idObra;
    }

    public void setIdObra(Integer idObra) {
        this.idObra = idObra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoEntity that = (EmpleadoEntity) o;
        return id == that.id && Objects.equals(dni, that.dni) && Objects.equals(nombre, that.nombre) && Objects.equals(sueldo, that.sueldo) && Objects.equals(idObra, that.idObra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, nombre, sueldo, idObra);
    }

    public ObraEntity getObraByIdObra() {
        return obraByIdObra;
    }

    public void setObraByIdObra(ObraEntity obraByIdObra) {
        this.obraByIdObra = obraByIdObra;
    }

    public Collection<MaquinariaEntity> getMaquinariasById() {
        return maquinariasById;
    }

    public void setMaquinariasById(Collection<MaquinariaEntity> maquinariasById) {
        this.maquinariasById = maquinariasById;
    }
}
