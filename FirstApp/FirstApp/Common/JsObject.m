//
// Created by pengwei on 2017/9/1.
// Copyright (c) 2017 hiandroid. All rights reserved.
//

#import "JsObject.h"
#import <AFNetworking/AFNetworking.h>


@implementation JsObject {

}

- (NSString *)testSync:(NSDictionary *)args {
    return [(NSString *) [args valueForKey:@"msg"] stringByAppendingString:@" sync call"];
}

- (NSString *)console:(NSDictionary *)args {
    NSLog(@"console log = %@", [args valueForKey:@"log"]);
    return nil;
}


- (NSString *)testAsync:(NSDictionary *)args :(void (^)(NSString *_Nullable result, BOOL isComplete))handler {
//    handler([(NSString *) [args valueForKey:@"msg"] stringByAppendingString:@"[ async call]"], YES);
    NSString *urlstr = @"http://m.kuaidi100.com/sysapi.do";
    NSDictionary *param = @{@"method": @"findxzqbyip"
    };
    AFHTTPSessionManager *manger = [AFHTTPSessionManager manager];
    manger.responseSerializer = [AFHTTPResponseSerializer serializer];
    [manger POST:urlstr parameters:param progress:nil success:^(NSURLSessionDataTask *_Nonnull task, id _Nullable responseObject) {
        NSString *str = [[NSString alloc] initWithData:responseObject encoding:NSUTF8StringEncoding];
        handler(str, YES);
    } failure:^(NSURLSessionDataTask *_Nullable task, NSError *_Nonnull error) {
        handler(@"请求失败", YES);
    }];
    return nil;
}

@end