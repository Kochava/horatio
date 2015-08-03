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
import java.util.List;

public class ListType extends CompositeType {

    private ListType(String name, TypeRef elemType) {
        super(name, Category.CONTAINER, elemType);
    }
    
    public Type getElementType() {
        return getElementTypes().get(0);
    }

    public static String nameForElemType(TypeRef elemType) {
        return "list<"+elemType.getTypeName()+">";
    }
    
    public static final ListType valueOf(TypeRef elemType, ModelSet modelSet) {
        String name = nameForElemType(elemType);
        return (ListType)modelSet.registerTypeIfNew(name, new ListType(name, elemType));
    }
    
    @Override
    public Object validate(Object value) throws ModelException {
        if (value instanceof ConstDef) {
            ConstDef cdef = (ConstDef)value;
            value = cdef.getValue();
            if (cdef.getType() == this) {
                return value;
            }
        }
        if (value instanceof List) {
            List<Object> list = new ArrayList<Object>(((List<?>)value).size());
            Type elemType = getElementType();
            for (Object elem : (List<?>)value) {
                list.add(elemType.validate(elem));
            }
            return list;
        } else {
            return super.validate(value); // will throw
        }
    }

}
