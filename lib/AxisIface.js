import {PropTypes} from 'react';

export const axisIface = {
  // what is drawn
  enabled: PropTypes.bool,
  drawLabels: PropTypes.bool,
  drawAxisLine: PropTypes.bool,
  drawGridLines: PropTypes.bool,

  // style
  textColor: PropTypes.string,
  textSize: PropTypes.number,
  fontFamily: PropTypes.string,
  fontStyle: PropTypes.number,
  gridColor: PropTypes.string,
  gridLineWidth: PropTypes.number,
  axisLineColor: PropTypes.string,
  axisLineWidth: PropTypes.number,
  gridDashedLine: PropTypes.shape({
    lineLength: PropTypes.number,
    spaceLength: PropTypes.number,
    phase: PropTypes.number
  }),

  // limit lines
  limitLines: PropTypes.arrayOf(
    PropTypes.shape({
      limit: PropTypes.number.isRequired,
      label: PropTypes.string,
      lineColor: PropTypes.string,
      lineWidth: PropTypes.number,
    })
  ),
  drawLimitLinesBehindData: PropTypes.bool,

  axisMaximum:PropTypes.number,
  axisMinimum:PropTypes.number,
  granularity: PropTypes.number,
  granularityEnabled: PropTypes.bool,

  labelCount:PropTypes.number,
  labelCountForce: PropTypes.bool,

  // formatting
  valueFormatter: PropTypes.oneOfType([
    PropTypes.oneOf(['largeValue', 'percent']),
    PropTypes.string,
    PropTypes.arrayOf(PropTypes.string)
  ]),
};

export const xAxisIface = {
  ...axisIface,

  labelRotationAngle:PropTypes.number,
  avoidFirstLastClipping:PropTypes.bool,
  position: PropTypes.string,
};

export const yAxisIface = {
  ...axisIface,

  inverted: PropTypes.bool,
  spaceTop: PropTypes.number,
  spaceBottom: PropTypes.number,

  position: PropTypes.string,

  maxWidth:PropTypes.number,
  minWidth:PropTypes.number,

  // zero line
  zeroLine: PropTypes.shape({
    enabled: PropTypes.bool,
    lineWidth: PropTypes.number,
    lineColor: PropTypes.string
  })
};
