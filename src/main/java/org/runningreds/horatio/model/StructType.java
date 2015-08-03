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

package org.runningreds.horatio.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructType extends Type implements Named {
    
    public enum Variant {
        STRUCT,
        UNION,
        INTERFACE
    }

    private final Variant variant;
    private final TypeRef baseType;
    private final List<TypeRef> interfaceTypes;
    private final FieldSet fields;
    private final Map<String, Object> annotations = Collections.synchronizedMap(new HashMap<String,Object>(2,2));
    private final List<String> docComments;
    
    
    public StructType(Model schema, String name, Variant variant, TypeRef baseType,
            List<TypeRef> interfaceTypes, List<String> docComments) {
        super(schema, name, Category.STRUCT);
        this.baseType = baseType;
        this.interfaceTypes = interfaceTypes;
        this.variant = variant;
        this.fields = new FieldSet(getQName());
        this.docComments = docComments;
    }

    public StructType(Model schema, String name, Variant variant) {
        this(schema, name, variant, (TypeRef)null, (List<TypeRef>)null, (List<String>)null);
    }
    
    public StructType getBaseType() {
        return baseType == null ? null : (StructType)baseType.getType();
    }
    
    public boolean hasInterfaces() {
        return interfaceTypes != null && interfaceTypes.size() > 0;
    }
    
    public List<StructType> getInterfaceTypes() {
        if (interfaceTypes == null) {
            return Collections.emptyList();
        }
        List<StructType> types = new ArrayList<StructType>(interfaceTypes.size());
        for (TypeRef ref : interfaceTypes) {
            types.add((StructType)ref.getType());
        }
        return types;
    }
    
    public Variant getVariant() {
        return variant;
    }
    
    public boolean isUnion() {
        return variant == Variant.UNION;
    }
    
    public boolean isInterface() {
        return variant == Variant.INTERFACE;
    }

    public void addField(FieldDef field) throws ModelException {
        fields.addField(field);
    }

    public List<FieldDef> getFields() {
        return fields.getFields();
    }

    public boolean hasField(String name) {
        return fields.hasField(name);
    }

    public FieldDef getField(String name) {
        return fields.getField(name);
    }

    public boolean hasFields() {
        return fields.hasFields();
    }

    public List<FieldDef> getTransientFields() {
        return fields.getTransientFields();
    }

    public boolean hasTransientFields() {
        return fields.hasTransientFields();
    }

    public List<FieldDef> getCombinedFields() {
        return fields.getCombinedFields();
    }

    public void addAnnotation(String name, Object value) {
        annotations.put(name, value);
    }
    
    public boolean hasAnnotations() {
        return !annotations.isEmpty();
    }

    public Map<String, Object> getAnnotations() {
        return annotations;
    }
    
    public List<String> getDocComments() {
        return docComments;
    }
    
}
