package entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "maquinaria", schema = "constructora", catalog = "")
public class MaquinariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "matricula", nullable = false, length = 7)
    private String matricula;
    @Basic
    @Column(name = "modelo", nullable = false, length = 20)
    private String modelo;
    @Basic
    @Column(name = "empleado", nullable = true, length = 9)
    private String empleado;
    @Basic
    @Column(name = "nombreObra", nullable = true, length = 50)
    private String nombreObra;
    @ManyToOne
    @JoinColumn(name = "empleado", referencedColumnName = "dni")
    private EmpleadoEntity empleadoByEmpleado;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
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
        MaquinariaEntity that = (MaquinariaEntity) o;
        return Objects.equals(matricula, that.matricula) && Objects.equals(modelo, that.modelo) && Objects.equals(empleado, that.empleado) && Objects.equals(nombreObra, that.nombreObra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, modelo, empleado, nombreObra);
    }

    public EmpleadoEntity getEmpleadoByEmpleado() {
        return empleadoByEmpleado;
    }

    public void setEmpleadoByEmpleado(EmpleadoEntity empleadoByEmpleado) {
        this.empleadoByEmpleado = empleadoByEmpleado;
    }
}
