package com.validator;

import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
	   Stack<HtmlTag> stack_tag = new Stack<HtmlTag>();
       HtmlTag tag_entry;
       while(tags!=null&&tags.size()!=0)
        {
        	tag_entry= tags.remove();
			if(tag_entry.isOpenTag())
			stack_tag.add(tag_entry);
			else
			{
				if(!tag_entry.isSelfClosing())
				{
					if(stack_tag==null||stack_tag.isEmpty())
					return null;
					else
					{
						if(tag_entry.matches(stack_tag.peek()))
						stack_tag.pop();
						else
						return stack_tag;
					}
				}
			}

		}
		return stack_tag;
	}
	
}

