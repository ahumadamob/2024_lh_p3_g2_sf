	package imb.lh_p3.clinica.entity;
	
	import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	import jakarta.persistence.*;
	import jakarta.validation.constraints.Email;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.NotNull;
	import jakarta.validation.constraints.Size;

	import java.util.List;

	@Entity
	public class Medico{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long idMedico;

		private Long idEspecialidad;

		@NotNull(message = "El nombre es obligatorio")
		@NotBlank(message = "El nombre no puede estar en blanco")
		@Size(min = 2, max = 100, message = "El nombre debe tener como minimo 2 caracteres")
		private String nombre;

		@NotNull(message = "El apellido es obligatorio")
		@NotBlank(message = "El apellido no puede estar en blanco")
		@Size(min = 2, max = 100, message = "El apellido debe tener como minimo 2 caracteres")
		private String apellido;

		private String matricula;

		@Email(message = "Debe proporcionar un correo electrónico válido")
		private String correo;

		@ManyToOne
		@JoinColumn(name = "idEspecialidad", insertable = false, updatable = false)
		@JsonIgnoreProperties("medicos")
		private Especialidad especialidad;

		@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JsonIgnoreProperties("medico") // Ignora el medico en los turnos
		private List<Turno> turnos;

		public Long getIdMedico() {
			return idMedico;
		}

		public void setIdMedico(Long idMedico) {
			this.idMedico = idMedico;
		}

		public Long getIdEspecialidad() {
			return idEspecialidad;
		}

		public void setIdEspecialidad(Long idEspecialidad) {
			this.idEspecialidad = idEspecialidad;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getMatricula() {
			return matricula;
		}

		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public Especialidad getEspecialidad() {
			return especialidad;
		}

		public void setEspecialidad(Especialidad especialidad) {
			this.especialidad = especialidad;
		}
	}
