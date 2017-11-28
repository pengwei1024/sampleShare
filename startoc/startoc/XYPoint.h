//
// Created by zsn on 2017/8/27.
// Copyright (c) 2017 apkfuns. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface XYPoint : NSObject
@property int x, y;

- (void)setX:(int)xVal andY:(int)yVal;
@end

@implementation XYPoint
@synthesize x,y;

- (void)setX:(int)xVal andY:(int)yVal {
    x = xVal;
    y = yVal;
}
@end