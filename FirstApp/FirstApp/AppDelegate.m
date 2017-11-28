//
//  AppDelegate.m
//  FirstApp
//
//  Created by pengwei on 2017/8/30.
//  Copyright © 2017 hiandroid. All rights reserved.
//

#import "AppDelegate.h"
#import "CommonUtils.h"
#import "MainViewController.h"
#import "ActionViewController.h"
#import "MeViewController.h"


@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // Override point for customization after application launch.
    UIImage *tabImage = nil;
    UIImage *tabImageHighlight = nil;
    // Main
    MainViewController *mainViewController = [[MainViewController alloc] init];
    mainViewController.title = @"Main";
    tabImage = [[CommonUtils imageWithColor:[UIColor redColor] size:CGSizeMake(30, 30)]
            imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
    tabImageHighlight = [[CommonUtils imageWithColor:[UIColor grayColor] size:CGSizeMake(30, 30)] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
    mainViewController.tabBarItem = [[UITabBarItem alloc] initWithTitle:mainViewController.title image:tabImage selectedImage:tabImageHighlight];
    UINavigationController *mainNavigationController = [[UINavigationController alloc] initWithRootViewController:mainViewController];

    // Action
    ActionViewController *actionViewController = [[ActionViewController alloc] init];
    actionViewController.title = @"Action";
    tabImage = [[CommonUtils imageWithColor:[UIColor greenColor] size:CGSizeMake(30, 30)] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
    tabImageHighlight = [[CommonUtils imageWithColor:[UIColor grayColor] size:CGSizeMake(30, 30)] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
    actionViewController.tabBarItem = [[UITabBarItem alloc] initWithTitle:actionViewController.title image:tabImage selectedImage:tabImageHighlight];
    UINavigationController *ActionNavigationController = [[UINavigationController alloc] initWithRootViewController:actionViewController];

    // Me
    MeViewController *meViewController = [[MeViewController alloc] init];
    meViewController.title = @"Me";
    tabImage = [[CommonUtils imageWithColor:[UIColor blueColor] size:CGSizeMake(30, 30)] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
    tabImageHighlight = [[CommonUtils imageWithColor:[UIColor grayColor] size:CGSizeMake(30, 30)] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
    meViewController.tabBarItem = [[UITabBarItem alloc] initWithTitle:meViewController.title image:tabImage selectedImage:tabImageHighlight];
    UINavigationController *meNavigationController = [[UINavigationController alloc] initWithRootViewController:meViewController];

    UITabBarController *uiTabBarController = [UITabBarController alloc].init;
    uiTabBarController.viewControllers = @[mainNavigationController, ActionNavigationController, meNavigationController];
    self.window.backgroundColor = [UIColor whiteColor];
    self.window.rootViewController = uiTabBarController;
    [self.window makeKeyAndVisible];

    return YES;
}


- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.

}


- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.

}


- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
}


- (void)applicationDidBecomeActive:(UIApplication *)application {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}


- (void)applicationWillTerminate:(UIApplication *)application {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}


@end