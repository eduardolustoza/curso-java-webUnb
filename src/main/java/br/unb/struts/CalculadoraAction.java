package br.unb.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.unb.calculadora.CalculadoraHelper;

public class CalculadoraAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("CalculadoraAction::execute");
		CalculadoraForm calcForm = (CalculadoraForm) form;
		String method = request.getParameter("method");
		System.out.println("CalculadoraAction::execute::method " + method);
		if (method != null && !method.isEmpty()) {
			switch (method) {
			case "calcular":
				calcForm.setResultado(CalculadoraHelper.Calcular(calcForm.expressao));
				break;
			}
		}

		return mapping.findForward("success");
	}
}
