/*
 * Copyright (C) 2013
 * by 52 North Initiative for Geospatial Open Source Software GmbH
 * 
 * Contact: Andreas Wytzisk
 * 52 North Initiative for Geospatial Open Source Software GmbH
 * Martin-Luther-King-Weg 24
 * 48155 Muenster, Germany
 * info@52north.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.n52.sos.dataTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:broering@52north.org">Arne Broering</a>
 */
public class Procedure {

    /**
     * unique identifier of the procedure
     */
    private String id;

    /**
     * resource link of the procedure
     */
    private String resource;
    
    /**
     * the features of interest observed by this procedure.
     */
    private List<String> featuresOfInterestList;
    
    /**
     * the outputs of this procedure.
     */
    private List<Output> outputsList;
    
    /**
     * the IDs of supported aggregationTypes of this procedure.
     */
    private List<String> aggregationTypeIdList;
    

    /**
     * 
     */
    public Procedure(String id, String resource) {
        super();
        this.id = id;
        this.resource = resource;
    }

    public String getId()
    {
        return id;
    }

    public String getResource()
    {
        return resource;
    }
    
    public List<String> getFeaturesOfInterest() 
    {
		return featuresOfInterestList;
	}

	public List<Output> getOutputs() 
	{
		return outputsList;
	}

	public List<String> getAggregationTypeIDs() 
    {
		return aggregationTypeIdList;
	}
	
	public void setFeaturesOfInterest(List<String> featuresOfInterest) {
		this.featuresOfInterestList = featuresOfInterest;
	}

	public void setOutputs(List<Output> outputs) {
		this.outputsList = outputs;
	}

	public void addOutput(String property, String propertyLabel, String unitNotation) {
		if (this.outputsList == null) {
			this.outputsList = new ArrayList<Output>();
		}
		Output output = new Output(property, propertyLabel, unitNotation);
		if (! this.outputsList.contains(output)) {
			this.outputsList.add(output);
		}
	}
	
	/**
	 * @return the Output of this Procedure with the given propertyID and propertyLabel.
	 * It returns <code>null</code> if no Output with that propertyID and propertyLabel 
	 * is associated with this Procedure.
	 */
	public Output getOutput(String propertyID, String propertyLabel, String unitNotation) {
		int index = this.outputsList.indexOf(new Output(propertyID, propertyLabel, unitNotation));
		if (index != -1) {
			return this.outputsList.get(index);
		}
		else {
			return null;
		}
	}
	
	public void addFeatureOfInterest(String featureID) {
		if (this.featuresOfInterestList == null) {
			this.featuresOfInterestList = new ArrayList<String>();
		}
		if (! this.featuresOfInterestList.contains(featureID)) {
			this.featuresOfInterestList.add(featureID);
		}
	}
	
	public void addAggregationTypeID(String aggregationTypeID) {
		if (this.aggregationTypeIdList == null) {
			this.aggregationTypeIdList = new ArrayList<String>();
		}
		if (! aggregationTypeIdList.contains(aggregationTypeID)) {
			aggregationTypeIdList.add(aggregationTypeID);
		}
	}
	
	@Override
	public boolean equals(Object otherProcedure) {
		if (otherProcedure != null) {
			if (otherProcedure instanceof Procedure) {
				Procedure p = (Procedure) otherProcedure;
				if (this.getId().equalsIgnoreCase(p.getId())
						&& this.getResource().equalsIgnoreCase(p.getResource()) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
    public String toString() {
    	StringBuilder result = new StringBuilder("[Procedure: " + id + " [features: ");
    	
    	if (this.getFeaturesOfInterest() != null) {
	    	for (String featureID : this.getFeaturesOfInterest()) {
				result.append(featureID + " ");
			}
    	}
    	result.append("] [outputs: ");
    	
    	if (this.getOutputs() != null) {
	    	for (Output output : this.getOutputs()) {
				result.append(output.toString() + " ");
			}
    	}
    	result.append("]");
    	result.append("]");
    	
    	return result.toString();
    }

    /**
     * Represents the output of a Procedure.
     * 
     * @author <a href="mailto:broering@52north.org">Arne Broering</a>
     */
    public class Output {
    	
    	private String observedPropertyID;
    	
    	private String observedPropertyLabel;

    	private String unitNotation;
    	
    	public Output(String observedProperty, String propertyLabel, String unitNotation) {
    		this.observedPropertyID = observedProperty;
    		this.observedPropertyLabel = propertyLabel;
    		this.unitNotation = unitNotation;
    	}
    	
    	public String getUnit() {
    		return this.unitNotation;
    	}
    	
    	public String getObservedPropertyID() {
    		return this.observedPropertyID;
    	}
    	
    	public String getObservedPropertyLabel() {
    		return this.observedPropertyLabel;
    	}
    	
    	@Override
    	public boolean equals(Object otherOutput) {
    		if (otherOutput != null) {
    			if (otherOutput instanceof Output) {
    				Output p = (Output) otherOutput;
    				if (this.getObservedPropertyID().equalsIgnoreCase(p.getObservedPropertyID())
    						&& this.getObservedPropertyLabel().equalsIgnoreCase(p.getObservedPropertyLabel())
    						&& this.getUnit().equalsIgnoreCase(p.getUnit()))
    				{
    					return true;
    				}
    			}
    		}
    		return false;
    	}
    	
    	@Override
        public String toString() {
        	return "[Output: " + observedPropertyLabel + ", " + observedPropertyID + ", " + unitNotation + "]";
        }
    }
}