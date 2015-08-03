/**
 *  Copyright 2011-2015 Bill Dortch / RunningReds.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.runningreds.horatio.parser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;

import org.runningreds.horatio.FileRef;
import org.runningreds.horatio.ParseException;
import org.runningreds.horatio.model.ModelSet;
import org.runningreds.horatio.model.Model;

/**
 * Methods to invoke Genspec and TBaseModel parsers.
 * 
 * @author Bill Dortch @ gmail
 *
 */
public class Parsers {
    private Parsers() {}

    private static final String IDL_EXTENSION = ".idl";
    
    public static Map<String, Object> parseGenspec(File genspecFile) throws ParseException {
        try {
            return parseGenspec(genspecFile.getName(), new FileInputStream(genspecFile));
        } catch (Exception e) {
            throw new ParseException("Error parsing genspec file " + genspecFile.getAbsolutePath(), e);
        }
    }
    
    public static Map<String, Object> parseGenspec(URL genspecURL) throws ParseException {
        try {
            String filename = new File(genspecURL.getPath()).getName();
            return parseGenspec(filename, genspecURL.openStream());
        } catch (Exception e) {
            throw new ParseException("Error parsing genspec file " + genspecURL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseGenspec(String name, InputStream in) throws ParseException {
        try {
            // This ugliness brought to you by dependency on generated code that
            // Eclipse/Maven just can't cope with.
            BufferedInputStream bufferedIn = new BufferedInputStream(in);
            Class<?> c = Class.forName("org.runningreds.horatio.parser.genspec.GenspecParser");
            Constructor<?> ctor = c.getConstructor(String.class, InputStream.class);
            Object parser = ctor.newInstance(name, bufferedIn);
            Method m = c.getMethod("Genspec");
            return (Map<String, Object>)m.invoke(parser);
        } catch (Exception e) {
            throw new ParseException("Error parsing genspec file ", e);
        }
    }
    
    public static Model parseTBase(File tbaseIDLFile)  throws ParseException {
        try {
            String filename = tbaseIDLFile.getName();
            String modelName = filename.endsWith(IDL_EXTENSION) ? filename.substring(0, filename.length() - IDL_EXTENSION.length()) : filename;
            ModelSet modelSet = new ModelSet();
            modelSet.setModelPath(new FileRef(tbaseIDLFile.getParentFile()));
            return parseTBase(modelName, modelSet, new FileInputStream(tbaseIDLFile));
        } catch (Exception e) {
            throw new ParseException("Error parsing TBase IDL file " + tbaseIDLFile.getAbsolutePath(), e);
        }
    }
    
    public static Model parseTBase(URL tbaseURL) throws ParseException{
        try {
            String fileName = new File(tbaseURL.getPath()).getName();
            String modelName = fileName.endsWith(IDL_EXTENSION) ? fileName.substring(0, fileName.length() - IDL_EXTENSION.length()) : fileName;
            ModelSet modelSet = new ModelSet();
            String urlString = tbaseURL.toString();
            modelSet.setModelPath(new FileRef(new URL(urlString.substring(0, urlString.lastIndexOf('/')))));
            return parseTBase(modelName, modelSet, tbaseURL.openStream());
        } catch (Exception e) {
            throw new ParseException("Error parsing TBase IDL file " + tbaseURL, e);
        }
    }

    public static Model parseTBase(String modelName, ModelSet modelSet, InputStream in)  throws ParseException {
        try {
            // This ugliness brought to you by dependency on generated code that
            // Eclipse/Maven just can't cope with.
            BufferedInputStream bufferedIn = new BufferedInputStream(in);
            Class<?> c = Class.forName("org.runningreds.tbgen.parser.idl.TBaseParser");
            Constructor<?> ctor = c.getConstructor(ModelSet.class, String.class, InputStream.class);
            Object parser = ctor.newInstance(modelSet, modelName, bufferedIn);
            Method m = c.getMethod("Model");
            return (Model)m.invoke(parser);
        } catch (Exception e) {
            throw new ParseException("Error parsing TBase IDL file " + modelName, e);
        }
    }

}
