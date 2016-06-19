package accesoDatos;
/** 
 * Proyecto: Juego de la vida.
 *  Almacén de datos del programa. Utiliza patron Façade.
 *  @since: prototipo2.2
 *  @source: GestionDatos.java 
 *  @version: 1.0 - 2016/05/23 
 *  @author: ajp
 */

import java.util.List;

import accesoDatos.db4o.MundosDAO;
import accesoDatos.db4o.PatronesDAO;
import accesoDatos.db4o.SesionesDAO;
import accesoDatos.db4o.SimulacionesDAO;
import accesoDatos.mySql.UsuariosDAO;

import modelo.*;

public class GestionDatos {

	// Requerido por el patrón Singleton
	private static GestionDatos instancia;

	// Requerido por el patrón Fachada
	private UsuariosDAO usuariosDAO; 
	private SesionesDAO sesionesDAO;
	private SimulacionesDAO simulacionesDAO;
	private MundosDAO mundosDAO;
	private PatronesDAO patronesDAO;

	/**
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instancia.
	 */
	public static GestionDatos getInstancia() {
		if (instancia == null) {
			instancia = new GestionDatos();
		}
		return instancia;
	}
	
	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */
	private GestionDatos() {
		usuariosDAO = UsuariosDAO.getInstancia();
		sesionesDAO = SesionesDAO.getInstancia();
		patronesDAO = PatronesDAO.getInstancia();
		mundosDAO = MundosDAO.getInstancia();
		simulacionesDAO = SimulacionesDAO.getInstancia();
	}

	/**
	 *  Cierra almacenes de datos.
	 */
	public void cerrar() {
		usuariosDAO.cerrar();
		sesionesDAO.cerrar();
		patronesDAO.cerrar();
		mundosDAO.cerrar();
		simulacionesDAO.cerrar();
	}

	// FACHADA usuariosDAO
	/**
	 * Metodo fachada que obtiene un Usuario dado el id. 
	 * Reenvia petición al método DAO específico.
	 * @param idUsr - el idUsr de Usuario a obtener.
	 * @return - el Usuario encontrado; null si no existe.
	 */	
	public Usuario obtenerUsuario(String idUsr) {
		return usuariosDAO.obtener(idUsr);
	}

	/**
	 * Metodo fachada que obtiene un Usuario dado un objeto. 
	 * Reenvia petición al método DAO específico.
	 * @param usr - el objeto Usuario a obtener.
	 * @return - el Usuario encontrado; null si no existe.
	 */	
	public Usuario obtenerUsuario(Usuario usr) {
		return usuariosDAO.obtener(usr);
	}
	
	/**
	 * Metodo fachada para alta de un Usuario. 
	 * Reenvia petición al método DAO específico.
	 * @param usuario - el objeto Usuario a dar de alta.
	 * @throws DatosException - si ya existe.
	 */
	public void altaUsuario(Usuario usuario) throws DatosException {
		usuariosDAO.alta(usuario);
	}

	/**
	 * Metodo fachada para alta de un Usuario. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el idUsr de Usuario a dar de baja.
	 * @throws DatosException - si ya existe.
	 */
	public Usuario bajaUsuario(String idUsr) throws DatosException {
		return (Usuario) usuariosDAO.baja(idUsr);
	}

	/**
	 * Metodo fachada para modicar un Usuario. 
	 * Reenvia petición al método DAO específico.
	 * @param u - el objeto Usuario con los cambios.
	 * @throws DatosException - si no existe.
	 */
	public void actualizarUsuario(Usuario usr) throws DatosException {
		usuariosDAO.actualizar(usr);
	}

	/**
	 * Metodo fachada para obtener listado de todos
	 * los objetos en forma de texto.  
	 * Reenvia petición al método DAO específico.
	 * @return - el texto.
	 */
	public String toStringDatosUsuarios() {
		return usuariosDAO.listarDatos();
	}

	/**
	 * Metodo fachada para obtener IdUsr a partir 
	 * de un id equivalente. 
	 * Reenvia petición al método DAO específico.
	 * @param id - equivalente.
	 */
	public String getEquivalenciaId(String id) {
		return usuariosDAO.obtenerEquivalencia(id);
	}

	// FACHADA sesionesDAO
	/**
	 * Metodo fachada que obtiene un Usuario dado el idSesion. 
	 * Reenvia petición al método DAO específico.
	 * @param idSesion - el idUsr + fecha de la SesionUsuario a obtener.
	 * @return - la SesionUsuario encontrada; null si no existe.
	 */	
	public SesionUsuario obtenerSesion(String idSesion) {
		return sesionesDAO.obtener(idSesion);
	}

	/**
	 * Metodo fachada que obtiene un Usuario dado un objeto. 
	 * Reenvia petición al método DAO específico.
	 * @param sesion - la SesionUsuario a obtener.
	 * @return - la SesionUsuario encontrada; null si no existe.
	 */	
	public SesionUsuario obtenerSesion(SesionUsuario sesion) {
		return sesionesDAO.obtener(sesion);
	}
	
	/**
	 * Metodo fachada para alta de una SesionUsuario. 
	 * Reenvia petición al método DAO específico.
	 * @param sesion - el objeto SesionUsuario a dar de alta.
	 * @throws DatosException - si ya existe.
	 */
	public void altaSesion(SesionUsuario sesion) throws DatosException {
		sesionesDAO.alta(sesion);
	}

	/**
	 * Metodo fachada para baja de una SesionUsuario. 
	 * Reenvia petición al método DAO específico.
	 * @param idSesion - el idUsr + fecha de la SesionUsuario a dar de baja.
	 * @throws DatosException - si ya existe.
	 */
	public SesionUsuario bajaSesionUsuario(String idSesion) throws DatosException {
		return (SesionUsuario) sesionesDAO.baja(idSesion);
	}

	/**
	 * Metodo fachada para modicar una Sesión. 
	 * Reenvia petición al método DAO específico.
	 * @param sesion - el objeto SesionUsuario a modificar.
	 * @throws DatosException - si no existe.
	 */
	public void actualizarSesion(SesionUsuario sesion) throws DatosException {
		sesionesDAO.actualizar(sesion);
	}

	/**
	 * Metodo fachada para obtener listado de todos
	 * los objetos en forma de texto.  
	 * Reenvia petición al método DAO específico.
	 * @return - el texto.
	 */
	public String toStringDatosSesiones() {
		return sesionesDAO.listarDatos();
	}

	// FACHADA simulacionesDAO
	/**
	 * Metodo fachada que obtiene una Simulacion dado el idSimulacion. 
	 * Reenvia petición al método DAO específico.
	 * @param idSimulacion - el idUsr + fecha de la Simulacion a obtener.
	 * @return - la Simulacion encontrada; null si no existe.
	 */	
	public Simulacion obtenerSimulacion(String idSimulacion) {
		return simulacionesDAO.obtener(idSimulacion);
	}
	
	/**
	 * Metodo fachada que obtiene una Simulacion dado un objeto. 
	 * Reenvia petición al método DAO específico.
	 * @param simulacion - el objeto Simulacion a obtener.
	 * @return - la Simulacion encontrada; null si no existe.
	 */	
	public Simulacion obtenerSimulacion(Simulacion simulacion) {
		return simulacionesDAO.obtener(simulacion);
	}
	
	/**
	 * Metodo fachada que obtiene todas las simulaciones de un usuario. 
	 * Reenvia petición al método DAO específico.
	 * @param simulacion - el objeto Simulacion a obtener.
	 * @return - lista de simulaciones encontradas; null si no existe.
	 */	
	public List<Simulacion> obtenerSimulacionesUsuario(String idUsr) {
		return simulacionesDAO.obtenerTodasMismoUsr(idUsr);
	}
	
	/**
	 * Metodo fachada para alta de una Simulacion. 
	 * Reenvia petición al método DAO específico.
	 * @param simulacion - el objeto Simulacion a dar de alta.
	 * @throws DatosException - si ya existe.
	 */
	public void altaSimulacion(Simulacion simulacion) throws DatosException {
		simulacionesDAO.alta(simulacion);
	}

	/**
	 * Metodo fachada para baja de una Simulacion dado su idSimulacion. 
	 * Reenvia petición al método DAO específico.
	 * @param idSimulacion - el idUsr + fecha de la Simulacion a dar de baja.
	 * @throws DatosException - si ya existe.
	 */
	public Simulacion bajaSimulacion(String idSimulacion) throws DatosException {
		return (Simulacion) simulacionesDAO.baja(idSimulacion);
	}

	/**
	 * Metodo fachada para modicar una Simulacion. 
	 * Reenvia petición al método DAO específico.
	 * @param simulacion - el objeto Simulacion a modificar.
	 * @throws DatosException - si no existe.
	 */
	public void actualizarSimulacion(Simulacion simulacion) throws DatosException {
		simulacionesDAO.actualizar(simulacion);
	}

	/**
	 * Metodo fachada para obtener listado de todos
	 * los objetos en forma de texto.  
	 * Reenvia petición al método DAO específico.
	 * @return - el texto.
	 */
	public String toStringDatosSimulaciones() {
		return simulacionesDAO.listarDatos();
	}

	// FACHADA mundosDAO
	/**
	 * Metodo fachada para obtener un dado su nombre. 
	 * Reenvia petición al método DAO específico.
	 * @param nombre - el nombre de un Mundo a buscar.
	 * @return - el Mundo encontrado; null si no existe.
	 */
	public Mundo obtenerMundo(String nombre) {
		return mundosDAO.obtener(nombre);
	}

	/**
	 * Metodo fachada para obtener un dado un objeto. 
	 * Reenvia petición al método DAO específico.
	 * @param mundo - el objeto Mundo a buscar.
	 * @return - el Mundo encontrado; null si no existe.
	 */
	public Mundo obtenerMundo(Mundo mundo) {
		return mundosDAO.obtener(mundo);
	}
	
	/**
	 * Metodo fachada para alta de un Mundo. 
	 * Reenvia petición al método DAO específico.
	 * @param mundo - el objeto Mundo a dar de alta.
	 * @throws DatosException - si ya existe.
	 */
	public void altaMundo(Mundo mundo) throws DatosException {
		mundosDAO.alta(mundo);
	}

	/**
	 * Metodo fachada para baja de un Mundo. 
	 * Reenvia petición al método DAO específico.
	 * @param nombre - el nombre de un Mundo a dar de baja.
	 * @throws DatosException - si ya existe.
	 */
	public Mundo bajaMundo(String nombre) throws DatosException {
		return (Mundo) mundosDAO.baja(nombre);
	}

	/**
	 * Metodo fachada para modicar un Mundo. 
	 * Reenvia petición al método DAO específico.
	 * @param mundo - el objeto Mundo a modificar.
	 * @throws DatosException - si no existe.
	 */
	public void actualizarMundo(Mundo mundo)  throws DatosException {
		mundosDAO.actualizar(mundo);
	}

	/**
	 * Metodo fachada para obtener listado de todos
	 * los objetos en forma de texto.  
	 * Reenvia petición al método DAO específico.
	 * @return - el texto.
	 */
	public String toStringDatosMundos() {
		return mundosDAO.listarDatos();
	}

	// FACHADA patronesDAO
	/**
	 * Metodo fachada para obtener un Patron dado su nombre. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el nombre de Patron a buscar.
	 * @return - el Patron encontrado; null si no existe.
	 */
	public Patron obtenerPatron(String nombre) {
		return (Patron) patronesDAO.obtener(nombre);
	}

	/**
	 * Metodo fachada para obtener un Patron dado un objeto. 
	 * Reenvia petición al método DAO específico.
	 * @param patron - el objeto de Patron a buscar.
	 * @return - el Patron encontrado; null si no existe.
	 */
	public Patron obtenerPatron(Patron patron) {
		return (Patron) patronesDAO.obtener(patron);
	}
	
	/**
	 * Metodo fachada para alta de una Patron. 
	 * Reenvia petición al método DAO específico.
	 * @param patron - el objeto Patron a dar de alta.
	 * @throws DatosException - si ya existe.
	 */
	public void altaPatron(Patron patron) throws DatosException {
		patronesDAO.alta(patron);
	}

	/**
	 * Metodo fachada para baja de un Patron. 
	 * Reenvia petición al método DAO específico.
	 * @param nombre - el nombre de Patron a dar de baja.
	 * @throws DatosException - si ya existe.
	 */
	public Patron bajaPatron(String nombre) throws DatosException {
		return (Patron) patronesDAO.baja(nombre);
	}

	/**
	 * Metodo fachada para modicar un Patron. 
	 * Reenvia petición al método DAO específico.
	 * @param patron - el objeto Patron a modificar.
	 * @throws DatosException - si no existe.
	 */
	public void actualizarPatron(Patron patron) throws DatosException {
		patronesDAO.actualizar(patron);
	}

	/**
	 * Metodo fachada para obtener listado de todos
	 * los objetos en forma de texto.  
	 * Reenvia petición al método DAO específico.
	 * @return - el texto.
	 */
	public String toStringDatosPatrones() {
		return patronesDAO.listarDatos();
	}

} //class