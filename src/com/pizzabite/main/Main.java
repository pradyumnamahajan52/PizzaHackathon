package com.pizzabite.main;

import com.pizzabite.dao.AdminDao;
import com.pizzabite.dao.CustomerDao;
import com.pizzabite.dao.UserDao;
import com.pizzabite.entity.Orders;
import com.pizzabite.entity.OrdersDetails;
import com.pizzabite.entity.PizzaItem;
import com.pizzabite.entity.PizzaPricing;
import com.pizzabite.entity.User;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            short choice;

            User loggedInUser = null;

            do {
                System.out.println("--------------------Menu--------------");
                System.out.println("0 - Exit");
                System.out.println("1 - Sign Up");
                System.out.println("2 - Sign In");
                System.out.print("Enter your choice: ");
                choice = scan.nextShort();
                scan.nextLine();

                switch (choice) {
                    case 0:
                        System.out.println("Thank You");
                        break;
                    case 1: {
                        User user = new User();
                        user.acceptforRegistration(scan);
                        try (UserDao userDao = new UserDao()) {
                            int status = userDao.UserRegistration(user);
                            if (status > 0) {
                                System.out.println("User successfully registered");
                            } else {
                                System.out.println("User Registration Failed!");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                    case 2: {
                        User user = new User();
                        user.acceptforLogin(scan);
                        try (UserDao userDao = new UserDao()) {
                            loggedInUser = userDao.UserLogin(user);
                            if (loggedInUser == null) {
                                System.out.println("User may not exist or Email or Password not correct");
                            } else {
                                System.out.println("Login successful!");
                                short choice1;
                                do {
                                    System.out.println("-------------------Menu------------------");
                                    System.out.println("1 - Admin");
                                    System.out.println("2 - Customer");
                                    System.out.println("3 - Back to main menu");
                                    System.out.print("Enter your choice: ");
                                    choice1 = scan.nextShort();
                                    scan.nextLine();

                                    switch (choice1) {
                                        case 1:
                                            if (loggedInUser.isAdmin()) {
                                                adminMenu(scan);
                                            } else {
                                                System.out.println("You are not authorized to view the admin menu");
                                            }
                                            break;
                                        case 2:
                                            customerMenu(scan, loggedInUser);
                                            break;
                                        case 3:
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            break;
                                    }
                                } while (choice1 != 3);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void adminMenu(Scanner scan) throws Exception {
        short choice;
        do {
            System.out.println("-------------------Admin Menu------------------");
            System.out.println("1 - View All Orders");
            System.out.println("2 - Show order details");
            System.out.println("3 - Update Order Status to Dispatched");
            System.out.println("4 - Update Order Status to Delivered");
            System.out.println("5 - Back to main menu");
            System.out.print("Enter your choice: ");
            choice = scan.nextShort();
            scan.nextLine();

            switch (choice) {
                case 1:

                    try (AdminDao adminDao = new AdminDao()) {
                        List<Orders> orderList = adminDao.getPurchasedOrders();
                        orderList.forEach(System.out::println);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2: {

                    System.out.print("Enter Order ID: ");
                    int orderId = scan.nextInt();
                    scan.nextLine();
                    try (AdminDao adminDao = new AdminDao()) {
                        OrdersDetails orderDetails = adminDao.getOrderDetails(orderId);
                        System.out.println(orderDetails);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 3: {

                    System.out.print("Enter the order id to update - ");
                    int id = scan.nextInt();
                    scan.nextLine();

                    try (AdminDao AdminDao = new AdminDao()) {
                        int count = AdminDao.updateOrderDispatched(id, "dispatched");
                        if (count > 0) {
                            System.out.println("order status updated successfully...");
                        } else {
                            System.out.println("order updation failed...");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;

                }

                case 4: {

                    System.out.print("Enter the order id to update - ");
                    int id1 = scan.nextInt();
                    scan.nextLine();

                    try (AdminDao AdminDao = new AdminDao()) {
                        int count = AdminDao.updateOrderDispatched(id1, "delivered");
                        if (count > 0) {
                            System.out.println("order status updated successfully...");
                        } else {
                            System.out.println("order updation failed...");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;

                }

                case 5:

                    System.out.println("Returning to main menu...");

                    break;
                default:

                    System.out.println("Invalid choice. Please try again.");

                    break;
            }
        } while (choice != 5);
    }

    private static void customerMenu(Scanner scan, User loggedInUser) throws SQLException, Exception {
        try (CustomerDao customerDao = new CustomerDao()) {
            short choice;
            do {
                System.out.println("-------------------Customer Menu------------------");
                System.out.println("1 - Show Veg Items");
                System.out.println("2 - Show Non-Veg Items");
                System.out.println("3 - Show available sizes");
                System.out.println("4 - Add to cart");
                System.out.println("5 - Show Cart ");
                System.out.println("6 - Place Order");
                System.out.println("7 - Sign Out");
                System.out.print("Enter your choice: ");
                choice = scan.nextShort();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        List<PizzaItem> vegItems = customerDao.getVegItems();
                        vegItems.forEach(System.out::println);
                        break;
                    case 2:
                        List<PizzaItem> nonVegItems = customerDao.getNonVegItems();
                        nonVegItems.forEach(System.out::println);
                        break;
                    case 3:
                        System.out.print("Enter Item ID: ");
                        int itemId = scan.nextInt();
                        scan.nextLine();
                        List<PizzaPricing> sizes = customerDao.getAvailableSizes(itemId);
                        sizes.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.print("Enter Price ID: ");
                        int priceId = scan.nextInt();
                        scan.nextLine();

                        PizzaPricing pricing = customerDao.getPizzaPricingById(priceId);
                        if (pricing != null) {
                            customerDao.addToCart(pricing);
                        } else {
                            System.out.println("Invalid Price ID. Please try again.");
                        }
                        break;
                    case 5:
                        customerDao.showCart();
                        break;
                    case 6:
                        customerDao.placeOrder(loggedInUser.getId());
                        break;

                    case 7:
                        System.out.println("Signing out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 7);
        }
    }

}
