//
// Created by zsn on 2017/8/26.
// Copyright (c) 2017 apkfuns. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface ClassBase : NSObject

@property int goodTime;

- (void)print;

// 多个参数
- (void)multiArgs:(int)d :(double)x;

- (void)multiArgs2:(int)d key:(double)x;

// 对象作为参数
- (void) objectArgs:(ClassBase *) arg;

@end