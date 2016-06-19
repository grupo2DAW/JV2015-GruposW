package modelo;
/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Contraseña según el modelo2.  
 *  @since: prototipo2.0
 *  @source: Contraseña.java 
 *  @version: 1.0 - 14/03/2016 
 *  @author: ajp
 */
import java.io.Serializable;

import config.Configuracion;
import util.Formato;

public class Contraseña implements Serializable {

	private String texto;
	
	public Contraseña(String texto) {
		setTexto(texto);
	}

	public Contraseña() {
		this(Configuracion.get().getProperty("usuario.passwordPredeterminada"));
	}

	public Contraseña(Contraseña contraseña) {
		this(contraseña.texto);
	}

	public Contraseña(Object textoEncriptado) {
		assert textoEncriptado != null;
		texto = (String)textoEncriptado;
	}

	public void setTexto(String texto) {
		assert esValida(texto);
		this.texto = encriptar(texto);
	}
	
	/**
	 * Comprueba validez de una contraseña.
	 * @param texto.
	 * @return true si cumple.
	 */
	public static boolean esValida(String texto) {
		if (texto != null 
				&& util.Formato.validar(texto, Formato.PATRON_CONTRASEÑA3)) {
			return true;
		}
		return false;
	}

	/**
	 * Encripta una texto utilizando una clase de utilidad.
	 * @param claveAcceso - a encriptar.
	 * @return clave encriptada.
	 */
	private String encriptar(String claveAcceso) {	
		return util.Criptografia.cesar(claveAcceso);
	}
	
	@Override
	public String toString() {
		return texto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (getClass() == obj.getClass()) {
			if (texto.equals(((Contraseña)obj).texto)) {
				return true;
			}
		}
		return false;
	}

} // class
