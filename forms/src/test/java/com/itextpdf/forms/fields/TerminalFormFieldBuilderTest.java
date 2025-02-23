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
package com.itextpdf.forms.fields;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.test.annotations.type.UnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.ByteArrayOutputStream;

@Category(UnitTest.class)
public class TerminalFormFieldBuilderTest extends ExtendedITextTest {

    private static final PdfDocument DUMMY_DOCUMENT = new PdfDocument(new PdfWriter(new ByteArrayOutputStream()));
    private static final String DUMMY_NAME = "dummy name";
    private static final Rectangle DUMMY_RECTANGLE = new Rectangle(7, 11, 13, 17);

    @Test
    public void constructorTest() {
        TestBuilder builder = new TestBuilder(DUMMY_DOCUMENT, DUMMY_NAME);

        Assert.assertSame(DUMMY_DOCUMENT, builder.getDocument());
        Assert.assertSame(DUMMY_NAME, builder.getFormFieldName());
    }

    @Test
    public void getSetWidgetTest() {
        TestBuilder builder = new TestBuilder(DUMMY_DOCUMENT, DUMMY_NAME);
        builder.setWidgetRectangle(DUMMY_RECTANGLE);

        Assert.assertSame(DUMMY_RECTANGLE, builder.getWidgetRectangle());
    }

    @Test
    public void getSetPageTest() {
        TestBuilder builder = new TestBuilder(DUMMY_DOCUMENT, DUMMY_NAME);
        PdfPage page = DUMMY_DOCUMENT.addNewPage();
        builder.setPage(page);

        Assert.assertEquals(1, builder.getPage());

        builder.setPage(5);

        Assert.assertEquals(5, builder.getPage());
    }

    @Test
    public void setPageToFieldTest() {
        TestBuilder builder = new TestBuilder(DUMMY_DOCUMENT, DUMMY_NAME);
        builder.setPage(5);

        PdfFormAnnotation formFieldAnnot = new PdfFormAnnotation((PdfDictionary)new PdfDictionary().makeIndirect(DUMMY_DOCUMENT)) {
            @Override
            public PdfFormAnnotation setPage(int pageNum) {
                Assert.assertEquals(5, pageNum);
                return this;
            }
        };
        PdfFormField formField = new PdfFormField(DUMMY_DOCUMENT).addKid(formFieldAnnot);
        builder.setPageToField(formField);
    }

    private static class TestBuilder extends TerminalFormFieldBuilder<TestBuilder> {

        protected TestBuilder(PdfDocument document, String formFieldName) {
            super(document, formFieldName);
        }

        @Override
        protected TestBuilder getThis() {
            return this;
        }
    }
}
