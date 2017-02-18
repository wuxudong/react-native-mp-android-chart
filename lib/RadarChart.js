import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import ChartBase from './ChartBase';
import {yAxisIface} from './YAxisIface';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'RadarChart',
  propTypes: {
    ...ChartBase.propTypes,

    yAxis: PropTypes.shape(yAxisIface),

    skipWebLineCount: PropTypes.number,

    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        values: PropTypes.arrayOf(PropTypes.shape({
          y: PropTypes.number.isRequired,
        })),
        xLabels: PropTypes.arrayOf(PropTypes.string),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.lineScatterCandleRadar,
          ...ChartDataSetConfig.lineRadar

        })
      })),
    })
  }
};

export default requireNativeComponent('MPAndroidRadarChart', iface);
