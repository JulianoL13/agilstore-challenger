-- Create extension for UUID support
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create categories table
CREATE TABLE IF NOT EXISTS categories (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(100) NOT NULL,
    description TEXT
    );

-- Insert initial categories
INSERT INTO categories (name, description) VALUES
                                               ('Electronics', 'Devices and gadgets for everyday use'),
                                               ('Clothing', 'Apparel and accessories for all genders'),
                                               ('Home & Kitchen', 'Furniture, appliances, and decor for your home'),
                                               ('Sports', 'Sports equipment and outdoor gear'),
                                               ('Toys', 'Toys for children of all ages'),
                                               ('Books', 'Books for all ages and interests'),
                                               ('Beauty', 'Cosmetics and skincare products'),
                                               ('Health', 'Vitamins, supplements, and health products'),
                                               ('Food', 'Groceries and food items for all diets'),
                                               ('Pets', 'Pet food, toys, and accessories');

-- Create products table
CREATE TABLE IF NOT EXISTS products (
                                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    amount INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    category_id INTEGER REFERENCES categories(id)
    );

-- Insert initial products
INSERT INTO products (name, amount, price, category_id)
VALUES
    ('Smartphone', 50, 699.99, (SELECT id FROM categories WHERE name = 'Electronics')),
    ('Laptop', 30, 999.99, (SELECT id FROM categories WHERE name = 'Electronics')),
    ('T-Shirt', 100, 19.99, (SELECT id FROM categories WHERE name = 'Clothing')),
    ('Jeans', 75, 49.99, (SELECT id FROM categories WHERE name = 'Clothing')),
    ('Coffee Maker', 40, 79.99, (SELECT id FROM categories WHERE name = 'Home & Kitchen')),
    ('Microwave', 25, 149.99, (SELECT id FROM categories WHERE name = 'Home & Kitchen')),
    ('Basketball', 60, 29.99, (SELECT id FROM categories WHERE name = 'Sports')),
    ('Yoga Mat', 45, 24.99, (SELECT id FROM categories WHERE name = 'Sports')),
    ('LEGO Set', 80, 59.99, (SELECT id FROM categories WHERE name = 'Toys')),
    ('Board Game', 70, 34.99, (SELECT id FROM categories WHERE name = 'Toys')),
    ('Novel', 150, 14.99, (SELECT id FROM categories WHERE name = 'Books')),
    ('Cookbook', 90, 24.99, (SELECT id FROM categories WHERE name = 'Books')),
    ('Lipstick', 200, 19.99, (SELECT id FROM categories WHERE name = 'Beauty')),
    ('Face Cream', 120, 29.99, (SELECT id FROM categories WHERE name = 'Beauty')),
    ('Vitamins', 300, 24.99, (SELECT id FROM categories WHERE name = 'Health')),
    ('First Aid Kit', 150, 39.99, (SELECT id FROM categories WHERE name = 'Health')),
    ('Organic Coffee', 200, 12.99, (SELECT id FROM categories WHERE name = 'Food')),
    ('Chocolate Bar', 400, 3.99, (SELECT id FROM categories WHERE name = 'Food')),
    ('Dog Food', 100, 44.99, (SELECT id FROM categories WHERE name = 'Pets')),
    ('Cat Toy Set', 150, 15.99, (SELECT id FROM categories WHERE name = 'Pets'));