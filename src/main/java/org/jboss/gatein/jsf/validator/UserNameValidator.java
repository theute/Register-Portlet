/*
 *  Copyright (C) 2010 Red Hat, Inc. All rights reserved.
 *
 *  This is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU Lesser General Public License as
 *  published by the Free Software Foundation; either version 2.1 of
 *  the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this software; if not, write to the Free
 *  Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *  02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.gatein.jsf.validator;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * {@code UserNameValidator}
 *
 * Created on Dec 6, 2010, 10:05:03 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class UserNameValidator implements Validator {

    /**
     * Create a new instance of {@code UserNameValidator}
     */
    public UserNameValidator() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.validator.Validator.validate(javax.faces.context.FacesContext,
     *   javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        if (o != null) {
            if (!(o instanceof String)) {
                throw new IllegalArgumentException("The value must be a String");
            }
            String value = ((String) o).trim();
            HtmlInputText inputText = (HtmlInputText) uic;

            ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(fc, "msg");
            String validationMessage = null;


            if (value.matches("\\s*")) {
                validationMessage = resourceBundle.getString("javax.faces.component.UIInput.REQUIRED");
                if (validationMessage == null) {
                    validationMessage = "Value is required!";
                }

                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }

            String label = inputText.getLabel();
            if (value.equalsIgnoreCase(label)) {
                validationMessage = resourceBundle.getString("user.login.id.validation");
                if (validationMessage == null) {
                    validationMessage = "The introduced value is not valid!";
                }

                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }

            /*
            ExoContainer container = ExoContainerContext.getContainerByName("portal");
            OrganizationService orgService = (OrganizationService) container.getComponentInstanceOfType(OrganizationService.class);
            UserHandler userHandler = orgService.getUserHandler();

            try {
                if (userHandler.findUserByName(value) != null) {
                    validationMessage = resourceBundle.getString("user.login.id.exists");
                    if (validationMessage == null) {
                        validationMessage = "The username is already used!";
                    }

                    throw new ValidatorException(
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
                }
            } catch (Exception exp) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while accessing database", exp.getMessage()));
            }
            */
            validationMessage = resourceBundle.getString("user.login.id.accepted");
            if (validationMessage == null) {
                validationMessage = "The username is accepted";
            }
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, validationMessage, validationMessage);
            fc.addMessage(uic.getClientId(fc), message);
        }
    }
}