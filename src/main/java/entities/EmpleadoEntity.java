package entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "empleado", schema = "constructora", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dni", nullable = false, length = 9)
    private String dni;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "sueldo", nullable = true, precision = 0)
    private Double sueldo;
    @Basic
    @Column(name = "nombreObra", nullable = true, length = 50)
    private String nombreObra;
    @OneToMany(mappedBy = "empleadoByEmpleado")
    private Collection<MaquinariaEntity> maquinariasByDni;

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

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoEntity that = (EmpleadoEntity) o;
        return Objects.equals(dni, that.dni) && Objects.equals(nombre, that.nombre) && Objects.equals(sueldo, that.sueldo) && Objects.equals(nombreObra, that.nombreObra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre, sueldo, nombreObra);
    }

    public Collection<MaquinariaEntity> getMaquinariasByDni() {
        return maquinariasByDni;
    }

    public void setMaquinariasByDni(Collection<MaquinariaEntity> maquinariasByDni) {
        this.maquinariasByDni = maquinariasByDni;
    }
}
