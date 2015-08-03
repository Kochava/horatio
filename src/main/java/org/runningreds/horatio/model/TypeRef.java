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

public class TypeRef {
    
    private final ModelSet modelSet;
    private final String typeName;
    private final String refLocation;
    private volatile Type type;
    
    public TypeRef(ModelSet modelSet, String typeName, String refLocation) {
        this.modelSet = modelSet;
        this.typeName = typeName;
        this.refLocation = refLocation;
    }
    
    public TypeRef(String typeName, Type type) {
        this.typeName = typeName;
        this.type = type;
        this.modelSet = null;
        this.refLocation = null;
    }
    
    public Type getType() {
        Type t;
        if ((t = type) != null) {
            return t;
        }
        if ((t = modelSet.getType(typeName)) != null) {
            return type = t;
        }
        throw new ModelException("Undefined type " + typeName + " referenced at location " + refLocation);
    }

    public String getTypeName() {
        return typeName;
    }

}
