import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'BarChart',
  propTypes: {
    ...BarLineChartBase.propTypes,

    drawValueAboveBar: PropTypes.bool,
    drawBarShadow: PropTypes.bool,
    drawHighlightArrow: PropTypes.bool,

    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        values: PropTypes.arrayOf(PropTypes.shape({
          x: PropTypes.number,
          y: PropTypes.oneOfType([
            PropTypes.number,
            PropTypes.arrayOf(PropTypes.number)
          ])
        })),
        xLabels: PropTypes.arrayOf(PropTypes.string),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.barLineScatterCandleBubble,

          barShadowColor: PropTypes.string,
          highlightAlpha: PropTypes.number,
          stackLabels: PropTypes.arrayOf(PropTypes.string)
        })
      }))
    })
  }
};

export default requireNativeComponent('MPAndroidBarChart', iface);
