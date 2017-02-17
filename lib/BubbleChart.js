import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import ChartBase from './ChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'BubbleChart',
  propTypes: {
    ...ChartBase.propTypes,

    data: PropTypes.shape({


      datasets: PropTypes.arrayOf(PropTypes.shape({


        values: PropTypes.shape({
          x : PropTypes.number.isRequired,
          y : PropTypes.number.isRequired,
          size: PropTypes.number.isRequired,
          payload : PropTypes.object
        }),

        yValues: PropTypes.arrayOf(
          PropTypes.shape({
            value: PropTypes.number.isRequired,
            size: PropTypes.number.isRequired
          })
        ),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.barLineScatterCandleBubble,

        })
      }))

    })
  }
};

export default requireNativeComponent('MPAndroidBubbleChart', iface);
