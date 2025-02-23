/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    This program is offered under a commercial and under the AGPL license.
    For commercial licensing, contact us at https://itextpdf.com/sales.  For AGPL licensing, see below.

    AGPL licensing:
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.itextpdf.forms.form.element;

import com.itextpdf.layout.element.IBlockElement;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class for fields that represents a control for selecting one or several of the provided options.
 */
public abstract class AbstractSelectField extends FormField<AbstractSelectField> {

    private final List<IBlockElement> options = new ArrayList<>();

    protected AbstractSelectField(String id) {
        super(id);
    }

    /**
     * Adds a container with option(s). This might be a container for options group.
     *
     * @param optionElement a container with option(s)
     */
    public void addOption(IBlockElement optionElement) {
        options.add(optionElement);
    }

    /**
     * Gets a list of containers with option(s). Every container might be a container for options group.
     *
     * @return a list of containers with option(s)
     */
    public List<IBlockElement> getOptions() {
        return options;
    }
}
