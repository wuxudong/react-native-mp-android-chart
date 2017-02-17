import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'LineChart',
  propTypes: {
    ...BarLineChartBase.propTypes,

    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        values: PropTypes.arrayOf(PropTypes.shape({
          x: PropTypes.number.isRequired,
          y: PropTypes.number.isRequired,
          payload: PropTypes.object
        })),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.barLineScatterCandleBubble,
          ...ChartDataSetConfig.lineScatterCandleRadar,
          ...ChartDataSetConfig.lineRadar,

          circleRadius: PropTypes.number,
          drawCircles: PropTypes.bool,
          mode: PropTypes.string,
          drawCubicIntensity: PropTypes.number,
          circleColor: PropTypes.string,
          circleColors: PropTypes.arrayOf(PropTypes.string),
          circleColorHole: PropTypes.string,
          drawCircleHole: PropTypes.bool,

          dashedLine: PropTypes.shape({
            lineLength: PropTypes.number.isRequired,
            spaceLength: PropTypes.number.isRequired,
            phase: PropTypes.number,
          })
        })
      }))

    })
  }
};

export default requireNativeComponent('MPAndroidLineChart', iface);
