import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'ScatterChart',
  propTypes: {
    ...BarLineChartBase.propTypes,

    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        values: PropTypes.arrayOf(PropTypes.shape({
          x: PropTypes.number,
          y: PropTypes.number.isRequired,
        })),
        xLabels: PropTypes.arrayOf(PropTypes.string),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.barLineScatterCandleBubble,
          ...ChartDataSetConfig.lineScatterCandleRadar,

          scatterShapeSize: PropTypes.number,
          scatterShape: PropTypes.oneOf(['SQUARE', 'CIRCLE', 'TRIANGLE', 'CROSS', 'X']),
          scatterShapeHoleColor: PropTypes.string,
          scatterShapeHoleRadius: PropTypes.number
        })
      }))
    })
  }
};

export default requireNativeComponent('MPAndroidScatterChart', iface);
