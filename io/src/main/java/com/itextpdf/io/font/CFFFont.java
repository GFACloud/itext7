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
package com.itextpdf.io.font;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.io.exceptions.IoExceptionMessageConstant;
import com.itextpdf.io.source.RandomAccessFileOrArray;
import com.itextpdf.io.source.RandomAccessSourceFactory;

import java.util.LinkedList;

public class CFFFont {

    static final String[] operatorNames = {
            "version", "Notice", "FullName", "FamilyName",
            "Weight", "FontBBox", "BlueValues", "OtherBlues",
            "FamilyBlues", "FamilyOtherBlues", "StdHW", "StdVW",
            "UNKNOWN_12", "UniqueID", "XUID", "charset",
            "Encoding", "CharStrings", "Private", "Subrs",
            "defaultWidthX", "nominalWidthX", "UNKNOWN_22", "UNKNOWN_23",
            "UNKNOWN_24", "UNKNOWN_25", "UNKNOWN_26", "UNKNOWN_27",
            "UNKNOWN_28", "UNKNOWN_29", "UNKNOWN_30", "UNKNOWN_31",
            "Copyright", "isFixedPitch", "ItalicAngle", "UnderlinePosition",
            "UnderlineThickness", "PaintType", "CharstringType", "FontMatrix",
            "StrokeWidth", "BlueScale", "BlueShift", "BlueFuzz",
            "StemSnapH", "StemSnapV", "ForceBold", "UNKNOWN_12_15",
            "UNKNOWN_12_16", "LanguageGroup", "ExpansionFactor", "initialRandomSeed",
            "SyntheticBase", "PostScript", "BaseFontName", "BaseFontBlend",
            "UNKNOWN_12_24", "UNKNOWN_12_25", "UNKNOWN_12_26", "UNKNOWN_12_27",
            "UNKNOWN_12_28", "UNKNOWN_12_29", "ROS", "CIDFontVersion",
            "CIDFontRevision", "CIDFontType", "CIDCount", "UIDBase",
            "FDArray", "FDSelect", "FontName"
    };

    static final String[] standardStrings = {
            // Automatically generated from Appendix A of the CFF specification; do
            // not edit. Size should be 391.
            ".notdef", "space", "exclam", "quotedbl", "numbersign", "dollar",
            "percent", "ampersand", "quoteright", "parenleft", "parenright",
            "asterisk", "plus", "comma", "hyphen", "period", "slash", "zero", "one",
            "two", "three", "four", "five", "six", "seven", "eight", "nine", "colon",
            "semicolon", "less", "equal", "greater", "question", "at", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z", "bracketleft", "backslash",
            "bracketright", "asciicircum", "underscore", "quoteleft", "a", "b", "c",
            "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "braceleft", "bar", "braceright",
            "asciitilde", "exclamdown", "cent", "sterling", "fraction", "yen",
            "florin", "section", "currency", "quotesingle", "quotedblleft",
            "guillemotleft", "guilsinglleft", "guilsinglright", "fi", "fl", "endash",
            "dagger", "daggerdbl", "periodcentered", "paragraph", "bullet",
            "quotesinglbase", "quotedblbase", "quotedblright", "guillemotright",
            "ellipsis", "perthousand", "questiondown", "grave", "acute", "circumflex",
            "tilde", "macron", "breve", "dotaccent", "dieresis", "ring", "cedilla",
            "hungarumlaut", "ogonek", "caron", "emdash", "AE", "ordfeminine", "Lslash",
            "Oslash", "OE", "ordmasculine", "ae", "dotlessi", "lslash", "oslash", "oe",
            "germandbls", "onesuperior", "logicalnot", "mu", "trademark", "Eth",
            "onehalf", "plusminus", "Thorn", "onequarter", "divide", "brokenbar",
            "degree", "thorn", "threequarters", "twosuperior", "registered", "minus",
            "eth", "multiply", "threesuperior", "copyright", "Aacute", "Acircumflex",
            "Adieresis", "Agrave", "Aring", "Atilde", "Ccedilla", "Eacute",
            "Ecircumflex", "Edieresis", "Egrave", "Iacute", "Icircumflex", "Idieresis",
            "Igrave", "Ntilde", "Oacute", "Ocircumflex", "Odieresis", "Ograve",
            "Otilde", "Scaron", "Uacute", "Ucircumflex", "Udieresis", "Ugrave",
            "Yacute", "Ydieresis", "Zcaron", "aacute", "acircumflex", "adieresis",
            "agrave", "aring", "atilde", "ccedilla", "eacute", "ecircumflex",
            "edieresis", "egrave", "iacute", "icircumflex", "idieresis", "igrave",
            "ntilde", "oacute", "ocircumflex", "odieresis", "ograve", "otilde",
            "scaron", "uacute", "ucircumflex", "udieresis", "ugrave", "yacute",
            "ydieresis", "zcaron", "exclamsmall", "Hungarumlautsmall",
            "dollaroldstyle", "dollarsuperior", "ampersandsmall", "Acutesmall",
            "parenleftsuperior", "parenrightsuperior", "twodotenleader",
            "onedotenleader", "zerooldstyle", "oneoldstyle", "twooldstyle",
            "threeoldstyle", "fouroldstyle", "fiveoldstyle", "sixoldstyle",
            "sevenoldstyle", "eightoldstyle", "nineoldstyle", "commasuperior",
            "threequartersemdash", "periodsuperior", "questionsmall", "asuperior",
            "bsuperior", "centsuperior", "dsuperior", "esuperior", "isuperior",
            "lsuperior", "msuperior", "nsuperior", "osuperior", "rsuperior",
            "ssuperior", "tsuperior", "ff", "ffi", "ffl", "parenleftinferior",
            "parenrightinferior", "Circumflexsmall", "hyphensuperior", "Gravesmall",
            "Asmall", "Bsmall", "Csmall", "Dsmall", "Esmall", "Fsmall", "Gsmall",
            "Hsmall", "Ismall", "Jsmall", "Ksmall", "Lsmall", "Msmall", "Nsmall",
            "Osmall", "Psmall", "Qsmall", "Rsmall", "Ssmall", "Tsmall", "Usmall",
            "Vsmall", "Wsmall", "Xsmall", "Ysmall", "Zsmall", "colonmonetary",
            "onefitted", "rupiah", "Tildesmall", "exclamdownsmall", "centoldstyle",
            "Lslashsmall", "Scaronsmall", "Zcaronsmall", "Dieresissmall", "Brevesmall",
            "Caronsmall", "Dotaccentsmall", "Macronsmall", "figuredash",
            "hypheninferior", "Ogoneksmall", "Ringsmall", "Cedillasmall",
            "questiondownsmall", "oneeighth", "threeeighths", "fiveeighths",
            "seveneighths", "onethird", "twothirds", "zerosuperior", "foursuperior",
            "fivesuperior", "sixsuperior", "sevensuperior", "eightsuperior",
            "ninesuperior", "zeroinferior", "oneinferior", "twoinferior",
            "threeinferior", "fourinferior", "fiveinferior", "sixinferior",
            "seveninferior", "eightinferior", "nineinferior", "centinferior",
            "dollarinferior", "periodinferior", "commainferior", "Agravesmall",
            "Aacutesmall", "Acircumflexsmall", "Atildesmall", "Adieresissmall",
            "Aringsmall", "AEsmall", "Ccedillasmall", "Egravesmall", "Eacutesmall",
            "Ecircumflexsmall", "Edieresissmall", "Igravesmall", "Iacutesmall",
            "Icircumflexsmall", "Idieresissmall", "Ethsmall", "Ntildesmall",
            "Ogravesmall", "Oacutesmall", "Ocircumflexsmall", "Otildesmall",
            "Odieresissmall", "OEsmall", "Oslashsmall", "Ugravesmall", "Uacutesmall",
            "Ucircumflexsmall", "Udieresissmall", "Yacutesmall", "Thornsmall",
            "Ydieresissmall", "001.000", "001.001", "001.002", "001.003", "Black",
            "Bold", "Book", "Light", "Medium", "Regular", "Roman", "Semibold"
    };

    //private String[] strings;
    public String getString(char sid) {
        if (sid < standardStrings.length) return standardStrings[sid];
        if (sid >= standardStrings.length+stringOffsets.length-1) return null;
        int j = sid - standardStrings.length;
        //java.lang.System.err.println("going for "+j);
        int p = getPosition();
        seek(stringOffsets[j]);
        StringBuffer s = new StringBuffer();
        for (int k=stringOffsets[j]; k<stringOffsets[j+1]; k++) {
            s.append(getCard8());
        }
        seek(p);
        return s.toString();
    }

    char getCard8() {
        try {
            byte i = buf.readByte();
            return (char)(i & 0xff);
        }
        catch (Exception e) {
            throw new IOException(IoExceptionMessageConstant.IO_EXCEPTION, e);
        }
    }

    char getCard16() {
        try {
            return buf.readChar();
        } catch (java.io.IOException e) {
            throw new IOException(IoExceptionMessageConstant.IO_EXCEPTION, e);
        }
    }

    int getOffset(int offSize) {
        int offset = 0;
        for (int i=0; i<offSize; i++) {
            offset *= 256;
            offset += getCard8();
        }
        return offset;
    }

    void seek(int offset) {
        buf.seek(offset);
    }

    short getShort() {
        try {
            return buf.readShort();
        } catch (java.io.IOException e) {
            throw new IOException(IoExceptionMessageConstant.IO_EXCEPTION, e);
        }
    }

    int getInt() {
        try {
            return buf.readInt();
        } catch (java.io.IOException e) {
            throw new IOException(IoExceptionMessageConstant.IO_EXCEPTION, e);
        }
    }

    int getPosition() {
        return (int)buf.getPosition();
    }
    int nextIndexOffset;
    // read the offsets in the next index
    // data structure, convert to global
    // offsets, and return them.
    // Sets the nextIndexOffset.
    int[] getIndex(int nextIndexOffset) {
        int count, indexOffSize;

        seek(nextIndexOffset);
        count = getCard16();
        int[] offsets = new int[count+1];

        if (count==0) {
            offsets[0] = -1;
            // TODO death store to local var .. should this be this.nextIndexOffset ?
            nextIndexOffset += 2;
            return offsets;
        }

        indexOffSize = getCard8();

        for (int j=0; j<=count; j++) {
            //nextIndexOffset = ofset to relative segment
            offsets[j] = nextIndexOffset
                    //2-> count in the index header. 1->offset size in index header
                    + 2+1
                    //offset array size * offset size
                    + (count+1)*indexOffSize
                    //???zero <-> one base
                    - 1
                    // read object offset relative to object array base
                    + getOffset(indexOffSize);
        }
        //nextIndexOffset = offsets[count];
        return offsets;
    }

    protected String   key;
    protected Object[] args      = new Object[48];
    protected int      arg_count = 0;

    protected void getDictItem() {
        for (int i=0; i<arg_count; i++) args[i]=null;
        arg_count = 0;
        key = null;
        boolean gotKey = false;

        while (!gotKey) {
            char b0 = getCard8();
            if (b0 == 29) {
                int item = getInt();
                args[arg_count] = item;
                arg_count++;
                //System.err.println(item+" ");
                continue;
            }
            if (b0 == 28) {
                short item = getShort();
                args[arg_count] = (int) item;
                arg_count++;
                //System.err.println(item+" ");
                continue;
            }
            if (b0 >= 32 && b0 <= 246) {
                args[arg_count] = b0-139;
                arg_count++;
                //System.err.println(item+" ");
                continue;
            }
            if (b0 >= 247 && b0 <= 250) {
                char b1 = getCard8();
                short item = (short) ((b0-247)*256+b1+108);
                args[arg_count] = (int) item;
                arg_count++;
                //System.err.println(item+" ");
                continue;
            }
            if (b0 >= 251 && b0 <= 254) {
                char b1 = getCard8();
                short item = (short) (-(b0-251)*256-b1-108);
                args[arg_count] = (int) item;
                arg_count++;
                //System.err.println(item+" ");
                continue;
            }
            if (b0 == 30) {
                StringBuilder item = new StringBuilder("");
                boolean done = false;
                char buffer = (char) 0;
                byte avail = 0;
                int  nibble = 0;
                while (!done) {
                    // get a nibble
                    if (avail==0) { buffer = getCard8(); avail=2; }
                    if (avail==1) { nibble = buffer / 16; avail--; }
                    if (avail==2) { nibble = buffer % 16; avail--; }
                    switch (nibble) {
                        case 0xa: item.append(".") ; break;
                        case 0xb: item.append("E") ; break;
                        case 0xc: item.append("E-"); break;
                        case 0xe: item.append("-") ; break;
                        case 0xf: done=true   ; break;
                        default:
                            if (nibble >= 0 && nibble <= 9)
                                item.append(nibble);
                            else {
                                item.append("<NIBBLE ERROR: ").append(nibble).append('>');
                                done = true;
                            }
                            break;
                    }
                }
                args[arg_count] = item.toString();
                arg_count++;
                //System.err.println(" real=["+item+"]");
                continue;
            }
            if (b0 <= 21) {
                gotKey=true;
                if (b0 != 12) key = operatorNames[b0];
                else key = operatorNames[32 + getCard8()];
                //for (int i=0; i<arg_count; i++)
                //  System.err.print(args[i].toString()+" ");
                //System.err.println(key+" ;");
                continue;
            }
        }
    }

    /** List items for the linked list that builds the new CID font.
     */

    protected static abstract class Item {
        protected int myOffset = -1;

        /**
         * Remember the current offset and increment by item's size in bytes.
         *
         * @param currentOffset increment offset by item's size
         */
        public void increment(int[] currentOffset) {
            myOffset = currentOffset[0];
        }

        /**
         * Emit the byte stream for this item.
         *
         * @param buffer byte array
         */
        public void emit(byte[] buffer) {}

        /**
         *  Fix up cross references to this item (applies only to markers).
         */
        public void xref() {}
    }

    protected static abstract class OffsetItem extends Item {
        public int value;

        /**
         * Set the value of an offset item that was initially unknown.
         * It will be fixed up latex by a call to xref on some marker.
         *
         * @param offset offset to set
         */
        public void set(int offset) { this.value = offset; }
    }


    /** A range item.
     */

    protected static final class RangeItem extends Item {
        public int offset, length;
        private RandomAccessFileOrArray buf;
        public RangeItem(RandomAccessFileOrArray buf, int offset, int length) {
            this.offset = offset;
            this.length = length;
            this.buf = buf;
        }
        @Override
        public void increment(int[] currentOffset) {
            super.increment(currentOffset);
            currentOffset[0] += length;
        }
        @Override
        public void emit(byte[] buffer) {
            //System.err.println("range emit offset "+offset+" size="+length);
            try {
                buf.seek(offset);
                for (int i=myOffset; i<myOffset+length; i++)
                    buffer[i] = buf.readByte();
            } catch (java.io.IOException e) {
                throw new IOException(IoExceptionMessageConstant.IO_EXCEPTION, e);
            }
            //System.err.println("finished range emit");
        }
    }

    /** An index-offset item for the list.
     * The size denotes the required size in the CFF. A positive
     * value means that we need a specific size in bytes (for offset arrays)
     * and a negative value means that this is a dict item that uses a
     * variable-size representation.
     */
    protected static final class IndexOffsetItem extends OffsetItem {
        public final int size;
        public IndexOffsetItem(int size, int value) {this.size=size; this.value=value;}
        public IndexOffsetItem(int size) {this.size=size; }

        @Override
        public void increment(int[] currentOffset) {
            super.increment(currentOffset);
            currentOffset[0] += size;
        }
        @Override
        public void emit(byte[] buffer) {
            if (size >= 1 && size <= 4) {
                for (int i = 0; i < size; i++) {
                    buffer[myOffset + i] = (byte) (value >>> ((size - 1 - i) << 3) & 0xFF);
                }
            }
        }
    }

    protected static final class IndexBaseItem extends Item {
        public IndexBaseItem() {}
    }

    protected static final class IndexMarkerItem extends Item {
        private OffsetItem offItem;
        private IndexBaseItem indexBase;
        public IndexMarkerItem(OffsetItem offItem, IndexBaseItem indexBase) {
            this.offItem   = offItem;
            this.indexBase = indexBase;
        }
        @Override
        public void xref() {
            //System.err.println("index marker item, base="+indexBase.myOffset+" my="+this.myOffset);
            offItem.set(this.myOffset-indexBase.myOffset+1);
        }
    }

    protected static final class SubrMarkerItem extends Item {
        private OffsetItem offItem;
        private IndexBaseItem indexBase;
        public SubrMarkerItem(OffsetItem offItem, IndexBaseItem indexBase) {
            this.offItem   = offItem;
            this.indexBase = indexBase;
        }
        @Override
        public void xref() {
            //System.err.println("index marker item, base="+indexBase.myOffset+" my="+this.myOffset);
            offItem.set(this.myOffset-indexBase.myOffset);
        }
    }


    /** an unknown offset in a dictionary for the list.
     * We will fix up the offset later; for now, assume it's large.
     */
    protected static final class DictOffsetItem extends OffsetItem {
        public final int size;
        public DictOffsetItem() {this.size=5; }

        @Override
        public void increment(int[] currentOffset) {
            super.increment(currentOffset);
            currentOffset[0] += size;
        }
        // this is incomplete!
        @Override
        public void emit(byte[] buffer) {
            if (size==5) {
                buffer[myOffset]   = 29;
                buffer[myOffset+1] = (byte) (value >>> 24 & 0xff);
                buffer[myOffset+2] = (byte) (value >>> 16 & 0xff);
                buffer[myOffset+3] = (byte) (value >>>  8 & 0xff);
                buffer[myOffset+4] = (byte) (value >>>  0 & 0xff);
            }
        }
    }

    /** Card24 item.
     */

    protected static final class UInt24Item extends Item {
        public int value;
        public UInt24Item(int value) {this.value=value;}

        @Override
        public void increment(int[] currentOffset) {
            super.increment(currentOffset);
            currentOffset[0] += 3;
        }
        // this is incomplete!
        @Override
        public void emit(byte[] buffer) {
            buffer[myOffset+0] = (byte) (value >>> 16 & 0xff);
            buffer[myOffset+1] = (byte) (value >>> 8 & 0xff);
            buffer[myOffset+2] = (byte) (value >>> 0 & 0xff);
        }
    }

    /** Card32 item.
     */

    protected static final class UInt32Item extends Item {
        public int value;
        public UInt32Item(int value) {this.value=value;}

        @Override
        public void increment(int[] currentOffset) {
            super.increment(currentOffset);
            currentOffset[0] += 4;
        }
        // this is incomplete!
        @Override
        public void emit(byte[] buffer) {
            buffer[myOffset+0] = (byte) (value >>> 24 & 0xff);
            buffer[myOffset+1] = (byte) (value >>> 16 & 0xff);
            buffer[myOffset+2] = (byte) (value >>> 8 & 0xff);
            buffer[myOffset+3] = (byte) (value >>> 0 & 0xff);
        }
    }

    /** A SID or Card16 item.
     */

    protected static final class UInt16Item extends Item {
        public char value;
        public UInt16Item(char value) {this.value = value;}

        @Override
        public void increment(int[] currentOffset) {
            super.increment(currentOffset);
            currentOffset[0] += 2;
        }
        // this is incomplete!
        @Override
        public void emit(byte[] buffer) {
//            Simplify from: there is no sense in >>> for unsigned char.
//            buffer[myOffset+0] = (byte) (value >>> 8 & 0xff);
//            buffer[myOffset+1] = (byte) (value >>> 0 & 0xff);
            buffer[myOffset+0] = (byte) (value >> 8 & 0xff);
            buffer[myOffset+1] = (byte) (value >> 0 & 0xff);
        }
    }

    /** A Card8 item.
     */

    protected static final class UInt8Item extends Item {
        public char value;
        public UInt8Item(char value) {this.value=value;}

        @Override
        public void increment(int[] currentOffset) {
            super.increment(currentOffset);
            currentOffset[0] += 1;
        }
        // this is incomplete!
        @Override
        public void emit(byte[] buffer) {
            //buffer[myOffset+0] = (byte) (value >>> 0 & 0xff);
            buffer[myOffset+0] = (byte) (value & 0xff);
        }
    }

    protected static final class StringItem extends Item {
        public String s;
        public StringItem(String s) {this.s=s;}

        @Override
        public void increment(int[] currentOffset) {
            super.increment(currentOffset);
            currentOffset[0] += s.length();
        }
        @Override
        public void emit(byte[] buffer) {
            for (int i=0; i<s.length(); i++)
                buffer[myOffset+i] = (byte) (s.charAt(i) & 0xff);
        }
    }


    /** A dictionary number on the list.
     * This implementation is inefficient: it doesn't use the variable-length
     * representation.
     */

    protected static final class DictNumberItem extends Item {
        public final int value;
        public int size = 5;
        public DictNumberItem(int value) {this.value=value;}
        @Override
        public void increment(int[] currentOffset) {
            super.increment(currentOffset);
            currentOffset[0] += size;
        }
        // this is incomplete!
        @Override
        public void emit(byte[] buffer) {
            if (size==5) {
                buffer[myOffset]   = 29;
                buffer[myOffset+1] = (byte) (value >>> 24 & 0xff);
                buffer[myOffset+2] = (byte) (value >>> 16 & 0xff);
                buffer[myOffset+3] = (byte) (value >>>  8 & 0xff);
                buffer[myOffset+4] = (byte) (value >>>  0 & 0xff);
            }
        }
    }

    /** An offset-marker item for the list.
     * It is used to mark an offset and to set the offset list item.
     */

    protected static final class MarkerItem extends Item {
        OffsetItem p;
        public MarkerItem(OffsetItem pointerToMarker) {p=pointerToMarker;}
        @Override
        public void xref() {
            p.set(this.myOffset);
        }
    }

    /** a utility that creates a range item for an entire index
     *
     * @param indexOffset where the index is
     * @return a range item representing the entire index
     */

    protected RangeItem getEntireIndexRange(int indexOffset) {
        seek(indexOffset);
        int count = getCard16();
        if (count==0) {
            return new RangeItem(buf,indexOffset,2);
        } else {
            int indexOffSize = getCard8();
            seek(indexOffset+2+1+count*indexOffSize);
            int size = getOffset(indexOffSize)-1;
            return new RangeItem(buf,indexOffset,
                    2+1+(count+1)*indexOffSize+size);
        }
    }


    /** get a single CID font. The PDF architecture (1.4)
     * supports 16-bit strings only with CID CFF fonts, not
     * in Type-1 CFF fonts, so we convert the font to CID if
     * it is in the Type-1 format.
     * Two other tasks that we need to do are to select
     * only a single font from the CFF package (this again is
     * a PDF restriction) and to subset the CharStrings glyph
     * description.
     *
     * @param fontName name of the font
     * @return byte array represents the CID font
     */
    public byte[] getCID(String fontName)
    //throws java.io.FileNotFoundException
    {
        int j;
        for (j=0; j<fonts.length; j++)
            if (fontName.equals(fonts[j].name)) break;
        if (j==fonts.length) return null;

        LinkedList<Item> l = new LinkedList<Item>();

        // copy the header

        seek(0);

        int major = getCard8();
        int minor = getCard8();
        int hdrSize = getCard8();
        int offSize = getCard8();
        nextIndexOffset = hdrSize;

        l.addLast(new RangeItem(buf,0,hdrSize));

        int nglyphs=-1, nstrings=-1;
        if ( ! fonts[j].isCID ) {
            // count the glyphs
            seek(fonts[j].charstringsOffset);
            nglyphs = getCard16();
            seek(stringIndexOffset);
            nstrings = getCard16()+standardStrings.length;
            //System.err.println("number of glyphs = "+nglyphs);
        }

        // create a name index

        // count
        l.addLast(new UInt16Item((char)1));
        // offSize
        l.addLast(new UInt8Item((char)1));
        // first offset
        l.addLast(new UInt8Item((char)1));
        l.addLast(new UInt8Item((char)( 1+fonts[j].name.length() )));
        l.addLast(new StringItem(fonts[j].name));

        // create the topdict Index

        // count
        l.addLast(new UInt16Item((char)1));
        // offSize
        l.addLast(new UInt8Item((char)2));
        // first offset
        l.addLast(new UInt16Item((char)1));
        OffsetItem topdictIndex1Ref = new IndexOffsetItem(2);
        l.addLast(topdictIndex1Ref);
        IndexBaseItem topdictBase = new IndexBaseItem();
        l.addLast(topdictBase);

        /*
        int maxTopdictLen = (topdictOffsets[j+1]-topdictOffsets[j])
                            + 9*2 // at most 9 new keys
                            + 8*5 // 8 new integer arguments
                            + 3*2;// 3 new SID arguments
         */

        //int    topdictNext = 0;
        //byte[] topdict = new byte[maxTopdictLen];

        OffsetItem charsetRef     = new DictOffsetItem();
        OffsetItem charstringsRef = new DictOffsetItem();
        OffsetItem fdarrayRef     = new DictOffsetItem();
        OffsetItem fdselectRef    = new DictOffsetItem();

        if ( !fonts[j].isCID ) {

            // create a ROS key
            l.addLast(new DictNumberItem(nstrings));
            l.addLast(new DictNumberItem(nstrings+1));
            l.addLast(new DictNumberItem(0));
            l.addLast(new UInt8Item((char)12));
            l.addLast(new UInt8Item((char)30));

            // create a CIDCount key
            l.addLast(new DictNumberItem(nglyphs));
            l.addLast(new UInt8Item((char)12));
            l.addLast(new UInt8Item((char)34));

            // What about UIDBase (12,35)? Don't know what is it.
            // I don't think we need FontName; the font I looked at didn't have it.
        }

        // create an FDArray key
        l.addLast(fdarrayRef);
        l.addLast(new UInt8Item((char)12));
        l.addLast(new UInt8Item((char)36));

        // create an FDSelect key
        l.addLast(fdselectRef);
        l.addLast(new UInt8Item((char)12));
        l.addLast(new UInt8Item((char)37));

        // create an charset key
        l.addLast(charsetRef);
        l.addLast(new UInt8Item((char)15));

        // create a CharStrings key
        l.addLast(charstringsRef);
        l.addLast(new UInt8Item((char)17));

        seek(topdictOffsets[j]);
        while (getPosition() < topdictOffsets[j+1]) {
            int p1 = getPosition();
            getDictItem();
            int p2 = getPosition();
            if ("Encoding".equals(key)
                    || "Private".equals(key)
                    || "FDSelect".equals(key)
                    || "FDArray".equals(key)
                    || "charset".equals(key)
                    || "CharStrings".equals(key)
                    ) {

            // just drop them
            } else {
                l.addLast(new RangeItem(buf,p1,p2-p1));
            }
        }

        l.addLast(new IndexMarkerItem(topdictIndex1Ref,topdictBase));

        // Copy the string index and append new strings.
        // We need 3 more strings: Registry, Ordering, and a FontName for one FD.
        // The total length is at most "Adobe"+"Identity"+63 = 76

        if (fonts[j].isCID) {
            l.addLast(getEntireIndexRange(stringIndexOffset));
        } else {
            String fdFontName = fonts[j].name+"-OneRange";
            if (fdFontName.length() > 127)
                fdFontName = fdFontName.substring(0,127);
            String extraStrings = "Adobe"+"Identity"+fdFontName;

            int origStringsLen = stringOffsets[stringOffsets.length-1]
                    - stringOffsets[0];
            int stringsBaseOffset = stringOffsets[0]-1;

            byte stringsIndexOffSize;
            if (origStringsLen+extraStrings.length() <= 0xff) stringsIndexOffSize = 1;
            else if (origStringsLen+extraStrings.length() <= 0xffff) stringsIndexOffSize = 2;
            else if (origStringsLen+extraStrings.length() <= 0xffffff) stringsIndexOffSize = 3;
            else stringsIndexOffSize = 4;

            // count
            l.addLast(new UInt16Item((char)(stringOffsets.length-1+3)));
            // offSize
            l.addLast(new UInt8Item((char)stringsIndexOffSize));
            for (int stringOffset : stringOffsets)
                l.addLast(new IndexOffsetItem(stringsIndexOffSize,
                        stringOffset-stringsBaseOffset));
            int currentStringsOffset = stringOffsets[stringOffsets.length-1]
                    - stringsBaseOffset;
            // l.addLast(new IndexOffsetItem(stringsIndexOffSize,currentStringsOffset));
            currentStringsOffset += "Adobe".length();
            l.addLast(new IndexOffsetItem(stringsIndexOffSize,currentStringsOffset));
            currentStringsOffset += "Identity".length();
            l.addLast(new IndexOffsetItem(stringsIndexOffSize,currentStringsOffset));
            currentStringsOffset += fdFontName.length();
            l.addLast(new IndexOffsetItem(stringsIndexOffSize,currentStringsOffset));

            l.addLast(new RangeItem(buf,stringOffsets[0],origStringsLen));
            l.addLast(new StringItem(extraStrings));
        }

        // copy the global subroutine index

        l.addLast(getEntireIndexRange(gsubrIndexOffset));

        // deal with fdarray, fdselect, and the font descriptors

        if (fonts[j].isCID) {
            // copy the FDArray, FDSelect, charset
        } else {
            // create FDSelect
            l.addLast(new MarkerItem(fdselectRef));
            // format identifier
            l.addLast(new UInt8Item((char)3));
            // nRanges
            l.addLast(new UInt16Item((char)1));

            // Range[0].firstGlyph
            l.addLast(new UInt16Item((char)0));
            // Range[0].fd
            l.addLast(new UInt8Item((char)0));

            // sentinel
            l.addLast(new UInt16Item((char)nglyphs));

            // recreate a new charset
            // This format is suitable only for fonts without subsetting

            l.addLast(new MarkerItem(charsetRef));
            // format identifier
            l.addLast(new UInt8Item((char)2));

            // first glyph in range (ignore .notdef)
            l.addLast(new UInt16Item((char)1));
            // nLeft
            l.addLast(new UInt16Item((char)(nglyphs-1)));

            // now all are covered, the data structure is complete.
            // create a font dict index (fdarray)

            l.addLast(new MarkerItem(fdarrayRef));
            l.addLast(new UInt16Item((char)1));
            // offSize
            l.addLast(new UInt8Item((char)1));
            // first offset
            l.addLast(new UInt8Item((char)1));

            OffsetItem privateIndex1Ref = new IndexOffsetItem(1);
            l.addLast(privateIndex1Ref);
            IndexBaseItem privateBase = new IndexBaseItem();
            l.addLast(privateBase);

            // looking at the PS that acrobat generates from a PDF with
            // a CFF opentype font embedded with an identity-H encoding,
            // it seems that it does not need a FontName.
            //l.addLast(new DictNumberItem((standardStrings.length+(stringOffsets.length-1)+2)));
            //l.addLast(new UInt8Item((char)12));
            //l.addLast(new UInt8Item((char)38)); // FontName

            l.addLast(new DictNumberItem(fonts[j].privateLength));
            OffsetItem privateRef = new DictOffsetItem();
            l.addLast(privateRef);
            // Private
            l.addLast(new UInt8Item((char)18));

            l.addLast(new IndexMarkerItem(privateIndex1Ref,privateBase));

            // copy the private index & local subroutines

            l.addLast(new MarkerItem(privateRef));
            // copy the private dict and the local subroutines.
            // the length of the private dict seems to NOT include
            // the local subroutines.
            l.addLast(new RangeItem(buf,fonts[j].privateOffset,fonts[j].privateLength));
            if (fonts[j].privateSubrs >= 0) {
                //System.err.println("has subrs="+fonts[j].privateSubrs+" ,len="+fonts[j].privateLength);
                l.addLast(getEntireIndexRange(fonts[j].privateSubrs));
            }
        }

        // copy the charstring index

        l.addLast(new MarkerItem(charstringsRef));
        l.addLast(getEntireIndexRange(fonts[j].charstringsOffset));

        // now create the new CFF font

        int[] currentOffset = new int[1];
        currentOffset[0] = 0;

        for (Item item : l) {
            item.increment(currentOffset);
        }

        for (Item item : l) {
            item.xref();
        }

        int size = currentOffset[0];
        byte[] b = new byte[size];

        for (Item item : l) {
            item.emit(b);
        }

        return b;
    }

    public boolean isCID() {
        return isCID(getNames()[0]);
    }

    public boolean isCID(String fontName) {
        int j;
        for (j=0; j<fonts.length; j++)
            if (fontName.equals(fonts[j].name)) return fonts[j].isCID;
        return false;
    }

    public boolean exists(String fontName) {
        int j;
        for (j=0; j<fonts.length; j++)
            if (fontName.equals(fonts[j].name)) return true;
        return false;
    }


    public String[] getNames() {
        String[] names = new String[ fonts.length ];
        for (int i=0; i<fonts.length; i++)
            names[i] = fonts[i].name;
        return names;
    }
    /**
     * A random Access File or an array
     */
    protected RandomAccessFileOrArray buf;
    private int offSize;

    protected int nameIndexOffset;
    protected int topdictIndexOffset;
    protected int stringIndexOffset;
    protected int gsubrIndexOffset;
    protected int[] nameOffsets;
    protected int[] topdictOffsets;
    protected int[] stringOffsets;
    protected int[] gsubrOffsets;

    protected final class Font {
        public String    name;
        public String    fullName;
        public boolean   isCID = false;
        // only if not CID
        public int       privateOffset     = -1;
        // only if not CID
        public int       privateLength     = -1;
        public int       privateSubrs      = -1;
        public int       charstringsOffset = -1;
        public int       encodingOffset    = -1;
        public int       charsetOffset     = -1;
        // only if CID
        public int       fdarrayOffset     = -1;
        // only if CID
        public int       fdselectOffset    = -1;
        public int[]     fdprivateOffsets;
        public int[]     fdprivateLengths;
        public int[]     fdprivateSubrs;

        // Added by Oren & Ygal
        public int nglyphs;
        public int nstrings;
        public int CharsetLength;
        public int[]    charstringsOffsets;
        public int[]    charset;
        public int[] 	FDSelect;
        public int FDSelectLength;
        public int FDSelectFormat;
        public int 		CharstringType = 2;
        public int FDArrayCount;
        public int FDArrayOffsize;
        public int[] FDArrayOffsets;
        public int[] PrivateSubrsOffset;
        public int[][] PrivateSubrsOffsetsArray;
        public int[]       SubrsOffsets;

        public int[] gidToCid;
    }
    // Changed from private to protected by Ygal&Oren
    protected Font[] fonts;

    RandomAccessSourceFactory rasFactory = new RandomAccessSourceFactory();

    public CFFFont(byte[] cff) {
        //System.err.println("CFF: nStdString = "+standardStrings.length);
        buf = new RandomAccessFileOrArray(rasFactory.createSource(cff));
        seek(0);

        int major, minor;
        major = getCard8();
        minor = getCard8();

        //System.err.println("CFF Major-Minor = "+major+"-"+minor);

        int hdrSize = getCard8();

        offSize = getCard8();

        //System.err.println("offSize = "+offSize);

        //int count, indexOffSize, indexOffset, nextOffset;

        nameIndexOffset    = hdrSize;
        nameOffsets        = getIndex(nameIndexOffset);
        topdictIndexOffset = nameOffsets[nameOffsets.length-1];
        topdictOffsets     = getIndex(topdictIndexOffset);
        stringIndexOffset  = topdictOffsets[topdictOffsets.length-1];
        stringOffsets      = getIndex(stringIndexOffset);
        gsubrIndexOffset   = stringOffsets[stringOffsets.length-1];
        gsubrOffsets       = getIndex(gsubrIndexOffset);

        fonts = new Font[nameOffsets.length-1];

        // now get the name index

        /*
        names             = new String[nfonts];
        privateOffset     = new int[nfonts];
        charsetOffset     = new int[nfonts];
        encodingOffset    = new int[nfonts];
        charstringsOffset = new int[nfonts];
        fdarrayOffset     = new int[nfonts];
        fdselectOffset    = new int[nfonts];
         */

        for (int j=0; j<nameOffsets.length-1; j++) {
            fonts[j] = new Font();
            seek(nameOffsets[j]);
            fonts[j].name = "";
            for (int k=nameOffsets[j]; k<nameOffsets[j+1]; k++) {
                fonts[j].name += getCard8();
            }
            //System.err.println("name["+j+"]=<"+fonts[j].name+">");
        }

        // string index

        //strings = new String[stringOffsets.length-1];
        /*
        System.err.println("std strings = "+standardStrings.length);
        System.err.println("fnt strings = "+(stringOffsets.length-1));
        for (char j=0; j<standardStrings.length+(stringOffsets.length-1); j++) {
            //seek(stringOffsets[j]);
            //strings[j] = "";
            //for (int k=stringOffsets[j]; k<stringOffsets[j+1]; k++) {
            //	strings[j] += (char)getCard8();
            //}
            System.err.println("j="+(int)j+" <? "+(standardStrings.length+(stringOffsets.length-1)));
            System.err.println("strings["+(int)j+"]=<"+getString(j)+">");
        }
         */

        // top dict

        for (int j=0; j<topdictOffsets.length-1; j++) {
            seek(topdictOffsets[j]);
            while (getPosition() < topdictOffsets[j+1]) {
                getDictItem();
                if (key=="FullName") {
                    //System.err.println("getting fullname sid = "+((Integer)args[0]).intValue());
                    fonts[j].fullName = getString((char)((Integer)args[0]).intValue());
                    //System.err.println("got it");
                } else if (key=="ROS")
                    fonts[j].isCID = true;
                else if (key=="Private") {
                    fonts[j].privateLength  = (int) ((Integer)args[0]).intValue();
                    fonts[j].privateOffset  = (int) ((Integer)args[1]).intValue();
                }
                else if (key=="charset"){
                    fonts[j].charsetOffset = (int) ((Integer)args[0]).intValue();

                }
//                else if (key=="Encoding"){
//                    int encOffset = ((Integer)args[0]).intValue();
//                    if (encOffset > 0) {
//                        fonts[j].encodingOffset = encOffset;
//                        ReadEncoding(fonts[j].encodingOffset);
//                    }
//                }
                else if (key=="CharStrings") {
                    fonts[j].charstringsOffset = (int) ((Integer)args[0]).intValue();
                    //System.err.println("charstrings "+fonts[j].charstringsOffset);
                    // Added by Oren & Ygal
                    int p = getPosition();
                    fonts[j].charstringsOffsets = getIndex(fonts[j].charstringsOffset);
                    seek(p);
                } else if (key=="FDArray")
                    fonts[j].fdarrayOffset = (int) ((Integer)args[0]).intValue();
                else if (key=="FDSelect")
                    fonts[j].fdselectOffset = (int) ((Integer)args[0]).intValue();
                else if (key=="CharstringType")
                    fonts[j].CharstringType = (int) ((Integer)args[0]).intValue();
            }

            // private dict
            if (fonts[j].privateOffset >= 0) {
                //System.err.println("PRIVATE::");
                seek(fonts[j].privateOffset);
                while (getPosition() < fonts[j].privateOffset+fonts[j].privateLength) {
                    getDictItem();
                    if (key=="Subrs")
                        //Add the private offset to the lsubrs since the offset is
                        // relative to the beginning of the PrivateDict
                        fonts[j].privateSubrs = (int) ((Integer)args[0]).intValue()+fonts[j].privateOffset;
                }
            }

            // fdarray index
            if (fonts[j].fdarrayOffset >= 0) {
                int[] fdarrayOffsets = getIndex(fonts[j].fdarrayOffset);

                fonts[j].fdprivateOffsets = new int[fdarrayOffsets.length-1];
                fonts[j].fdprivateLengths = new int[fdarrayOffsets.length-1];

                //System.err.println("FD Font::");

                for (int k=0; k<fdarrayOffsets.length-1; k++) {
                    seek(fdarrayOffsets[k]);
                    while (getPosition() < fdarrayOffsets[k+1]) {
                        getDictItem();
                        if (key=="Private") {
                            fonts[j].fdprivateLengths[k]  = (int) ((Integer)args[0]).intValue();
                            fonts[j].fdprivateOffsets[k]  = (int) ((Integer)args[1]).intValue();
                        }
                    }
                }
            }
        }
        //System.err.println("CFF: done");
    }

    // ADDED BY Oren & Ygal

    void ReadEncoding(int nextIndexOffset){
        int format;
        seek(nextIndexOffset);
        format = getCard8();
    }
}
