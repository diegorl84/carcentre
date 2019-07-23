package com.carcentre.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.carcentre.model.Mecanico;
import com.carcentre.model.MecanicoPK;
import com.carcentre.util.HibernateUtil;

@ManagedBean
@SessionScoped
public class CarCentreController {

	/**
	 * 
	 * @param mecanicoBean
	 * @return
	 */
	public String addNewMecanico(MecanicoBean mecanicoBean) {

		String urlString = "";

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trx = null;
		try {
			trx = session.beginTransaction();
			Mecanico mecanico = new Mecanico();

			MecanicoPK pk = new MecanicoPK();
			pk.setTipoDocumento(mecanicoBean.getTipoDocumento());
			pk.setDocumento(mecanicoBean.getDocumento());

			mecanico.setId(pk);

			mecanico.setPrimerNombre(mecanicoBean.getPrimerNombre());
			mecanico.setSegundoNombre(mecanicoBean.getSegundoNombre());
			mecanico.setPrimerApellido(mecanicoBean.getPrimerApellido());
			mecanico.setSegundoApellido(mecanicoBean.getSegundoApellido());
			mecanico.setCelular(mecanicoBean.getCelular());
			mecanico.setDireccion(mecanicoBean.getDireccion());
			mecanico.setEmail(mecanicoBean.getEmail());
			mecanico.setEstado(mecanicoBean.getEstado());

			session.save(mecanico);
			trx.commit();
		} catch (PersistenceException pe) {
			urlString = "/list-mecanicos.xhtml?msg=dbError";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		urlString = "/index.xhtml";

		return urlString;

	}

	/**
	 * Lista mecanicos por estado
	 * 
	 * @param status
	 * @return ArrayList de <code>MecanicosBean</code>
	 * 
	 */

	public List<MecanicoBean> listarMecanicosByStatus(String status) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trx = null;
		List<MecanicoBean> mecanicos = new ArrayList<MecanicoBean>();

		try {
			trx = session.beginTransaction();

			Query query = session.createQuery("FROM Mecanico WHERE estado = :estado");
			query.setParameter("estado", status);
			query.setMaxResults(10);
			mecanicos = query.getResultList();
			trx.commit();
		} catch (HibernateException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return mecanicos;
	}

}
