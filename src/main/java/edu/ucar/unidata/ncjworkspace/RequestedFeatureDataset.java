package edu.ucar.unidata.ncjworkspace;

import java.io.IOException;
import java.util.Formatter;
import ucar.nc2.NetcdfFile;
import ucar.nc2.constants.FeatureType;
import ucar.nc2.dt.RadialDatasetSweep;
import ucar.nc2.ft.FeatureDataset;
import ucar.nc2.ft.FeatureDatasetFactoryManager;
import ucar.nc2.ft.FeatureDatasetPoint;
import ucar.nc2.ft2.coverage.FeatureDatasetCoverage;

public class RequestedFeatureDataset {
  private NetcdfFile ncf;
  private FeatureDataset featureDataset;
  private FeatureType featureType;

  public RequestedFeatureDataset( String datasetLocation) throws IOException {
    Formatter errlog = new Formatter();

    this.featureDataset = FeatureDatasetFactoryManager.open( null, datasetLocation, null, errlog );
    if (this.featureDataset == null) {
      throw new IOException( String.format( "Failed to open FeatureDataset at %s %n %s %n", datasetLocation, errlog));
    }

    this.featureType = this.featureDataset.getFeatureType();

//    if (this.featureType.isCoverageFeatureType()) {
//      FeatureDatasetCoverage covDataset = (FeatureDatasetCoverage) featureDataset;
//
//    } else if (this.featureType.isPointFeatureType()) {
//      FeatureDatasetPoint pointDataset = (FeatureDatasetPoint) featureDataset;
//      throw new IOException( String.format( "Point Datasets not yet supported : %s %n", datasetLocation));
//    } else if (this.featureType == FeatureType.RADIAL) {
//      RadialDatasetSweep radialDataset = (RadialDatasetSweep) featureDataset;
//      throw new IOException( String.format( "Radial Datasets not yet supported : %s %n", datasetLocation));
//    }
  }

  public FeatureDataset getFeatureDataset() { return this.featureDataset; }
  public boolean isCoverageFeatureType() { return this.featureType.isCoverageFeatureType(); }
  public boolean isPointFeatureType() { return this.featureType.isPointFeatureType(); }

  public boolean isRadialFeatureType() {
    return (this.featureType == FeatureType.RADIAL
        || this.featureType == FeatureType.STATION_RADIAL);
  }


  public void close() throws IOException {
    this.featureDataset.close();
  }
}
