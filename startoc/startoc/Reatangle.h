//
// Created by zsn on 2017/8/27.
// Copyright (c) 2017 apkfuns. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "XYPoint.h"

// 矩形类
@interface Reatangle : NSObject
@property int width, height;
@property XYPoint *point;

- (void)setWidth:(int)w andHeight:(int)h;

- (int)area;

- (int)perimeter;

- (void)print;

- (void)setOriginal:(XYPoint *)p;
@end

@implementation Reatangle
@synthesize width, height, point;

- (void)setWidth:(int)w andHeight:(int)h {
    width = w;
    height = h;
}

- (int)area {
    return width * height;
}

- (int)perimeter {
    return (width + height) * 2;
}

- (void)print {
    NSLog(@"面积=%i, 周长=%i", self.area, self.perimeter);
}

- (void)setOriginal:(XYPoint *)p {
    point = p;
}


@end

// 正方形类
@interface Square : Reatangle
- (void)setSide:(int)s;

+ (void)main;
@end

@implementation Square
+ (void)main {

    Reatangle *rectangle = [Reatangle alloc].init;
    [rectangle setWidth:5 andHeight:8];
    [rectangle print];

    XYPoint *original = [XYPoint new];
    [original setX:50 andY:50];
    [rectangle setOriginal:original];
    NSLog(@"原点:(%i,%i)", rectangle.point.x, rectangle.point.y);

    Square *square = [Square alloc].init;
    [square setSide:10];
    [square print];

    id x = [Square new];
    [x setSide:-4];
    [x print];

    @try {

    } @catch (NSException * e) {
    }
}

- (void)setSide:(int)s {
    [super setWidth:s andHeight:s];
}
@end


