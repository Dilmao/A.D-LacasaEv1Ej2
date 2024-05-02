package entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "maquinaria", schema = "constructoraH", catalog = "")
public class MaquinariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "matricula", nullable = false, length = 7)
    private String matricula;
    @Basic
    @Column(name = "modelo", nullable = false, length = 20)
    private String modelo;
    @Basic
    @Column(name = "idEmpleado", nullable = true)
    private Integer idEmpleado;
    @Basic
    @Column(name = "idObra", nullable = true)
    private Integer idObra;
    @ManyToOne
    @JoinColumn(name = "idEmpleado", referencedColumnName = "id", insertable = false, updatable = false)
    private EmpleadoEntity empleadoByIdEmpleado;
    @ManyToOne
    @JoinColumn(name = "idObra", referencedColumnName = "id", insertable = false, updatable = false)
    private ObraEntity obraByIdObra;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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
        MaquinariaEntity that = (MaquinariaEntity) o;
        return id == that.id && Objects.equals(matricula, that.matricula) && Objects.equals(modelo, that.modelo) && Objects.equals(idEmpleado, that.idEmpleado) && Objects.equals(idObra, that.idObra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula, modelo, idEmpleado, idObra);
    }

    public EmpleadoEntity getEmpleadoByIdEmpleado() {
        return empleadoByIdEmpleado;
    }

    public void setEmpleadoByIdEmpleado(EmpleadoEntity empleadoByIdEmpleado) {
        this.empleadoByIdEmpleado = empleadoByIdEmpleado;
    }

    public ObraEntity getObraByIdObra() {
        return obraByIdObra;
    }

    public void setObraByIdObra(ObraEntity obraByIdObra) {
        this.obraByIdObra = obraByIdObra;
    }
}
