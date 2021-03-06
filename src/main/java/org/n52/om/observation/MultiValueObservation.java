/**
 * Copyright (C) 2012 52°North Initiative for Geospatial Open Source Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.om.observation;

import org.n52.gml.Identifier;
import org.n52.om.result.MultiMeasureResult;
import org.n52.oxf.valueDomains.time.ITime;

/**
 * @author <a href="mailto:broering@52north.org">Arne Broering</a>
 */
public class MultiValueObservation extends AbstractObservation {

	/**
	 * the {@link MultiMeasureResult} data structure containing all results
	 */
        protected MultiMeasureResult multiResult;
	
	protected String samplingPointID;
	
	protected String unitNotation;
	
	protected String aggregationType;

	private String unitLabel;
	
	public MultiValueObservation(
			Identifier identifier, 
			String procedure,
			String observedProperty, 
			String featureOfInterest,
			String samplingFeatureID,
			String unitID,
			String unitNotation,
			String unitLabel,
			String aggregationType,
			ITime resultTime) {
		super(identifier, 
				procedure, 
				observedProperty, 
				featureOfInterest,
				unitID,
				resultTime);
		this.multiResult = new MultiMeasureResult();
		this.samplingPointID = samplingFeatureID;
		this.unitNotation = unitNotation;
		this.unitLabel = unitLabel;
		this.aggregationType = applyStaticValueMapping(aggregationType);
	}


	@Override
	public MultiMeasureResult getResult() {
		return multiResult;
	}
	
	@Override
	public String getName() {
		return "OM_ComplexObservation";
	}
	
	public String getSamplingPoint() {
	    return samplingPointID;
	}
	
	public String getUnitNotation() {
            return unitNotation;
        }
	
	public String getAggregationType() {
            return aggregationType;
        }

	public String getUnitLabel() {
		return unitLabel;
	}
}
