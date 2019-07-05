/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.hui.project.generate;

import org.junit.Test;

/**
 * <p>
 * MysqlGeneratorTest
 * </p>
 *
 * @author Caratacus
 */
public class MysqlGeneratorTest {

	@Test
	public void generator() {
		MysqlGenerator mysqlGenerator = new MysqlGenerator();
		//mysqlGenerator.generator("slyx_dynamic_score_item", "0");

		//mysqlGenerator.generator("slyx_dynamic", "et","dto");
		//mysqlGenerator.generator("slyx_event", "et","dto");
		//mysqlGenerator.generator("slyx_event_note", "et","dto");
		//mysqlGenerator.generator("slyx_event_user_r", "et","dto");

		//mysqlGenerator.generator("slyx_schedule_event_r", "0","-c");
		//mysqlGenerator.generator("slyx_dynamic_content", "0","-c");
		//mysqlGenerator.generator("slyx_dynamic_timeline_r", "0","-c");
	}
}
