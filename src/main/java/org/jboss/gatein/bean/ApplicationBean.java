/*
 *  Copyright (C) 2010 Red Hat
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
package org.jboss.gatein.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.faces.model.SelectItem;

/**
 * {@code ApplicationBean}
 *
 * Created on Nov 15, 2010, 1:18:52 PM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class ApplicationBean {

    private List<SelectItem> counties;

    /**
     * Create a new instance of {@code ApplicationBean}
     */
    public ApplicationBean() {
        this.counties = new ArrayList<SelectItem>();
        fillCountries();
    }

    /**
     * Retrieve the list of all available countries and fill the list of select
     * items
     */
    private void fillCountries() {
        Locale[] locales = Locale.getAvailableLocales();
        String country = null;
        List<String> tmp = new ArrayList<String>();

        for (Locale locale : locales) {
            country = locale.getDisplayCountry();
            if (!country.equals("") && !tmp.contains(country)) {
                tmp.add(country);
            }
        }

        // need to sort the list to get a sorted list of countries
        Collections.sort(tmp);
        // add the list of available coutries to the list of select items
        for (String str : tmp) {
            this.counties.add(new SelectItem(str, str));
        }
    }

    /**
     * @return the counties
     */
    public List<SelectItem> getCounties() {
        return counties;
    }

    /**
     * @param counties the counties to set
     */
    public void setCounties(List<SelectItem> counties) {
        this.counties = counties;
    }
}
